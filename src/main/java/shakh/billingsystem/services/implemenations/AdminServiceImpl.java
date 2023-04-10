package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Company;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.ConfirmDto;
import shakh.billingsystem.models.RegistrationDto;
import shakh.billingsystem.repositories.AdminRepository;
import shakh.billingsystem.repositories.CompanyRepository;
import shakh.billingsystem.repositories.ReserveAdminRepository;
import shakh.billingsystem.services.AdminService;
import shakh.billingsystem.utilities.JwtTokenCreator;
import shakh.billingsystem.utilities.Utils;

import java.util.*;

import static shakh.billingsystem.utilities.Constants.USERNAME_NOT_FOUND;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService, UserDetailsService {

    private final AdminRepository adminRepository;
    private final ReserveAdminRepository reserveAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;



    @Override
    public UserDetails loadUserByUsername(String username) {

        Admins admin = adminRepository.findAdminsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND, username)));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        admin.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new User(admin.getUsername(), admin.getPassword(), authorities);
    }

    @Override
    public ApiResponse<?> register(RegistrationDto dto) {

        Optional<ReserveAdmin> check = Optional.ofNullable(reserveAdminRepository.findReserveAdminByUsername(dto.getUsername()));
        if (check.isPresent()) {
            return ApiResponse.builder()
                    .isError(true)
                    .message("Already registered")
                    .build();
        }

        if (!Utils.isPasswordValid(dto.getPassword())) {
            return ApiResponse.builder()
                    .isError(true)
                    .message("Password is invalid " +
                            "have at least 8 characters\n" +
                            "contain at least one letter\n" +
                            "contain at least one digit\n" +
                            "contain at least one special character (@, $, !, %, *, #, ?, &)")
                    .build();
        }
        if (Objects.isNull(dto.getPhoneNumber())) {
            return ApiResponse.builder()
                    .isError(true)
                    .message("Phone number must not be empty")
                    .build();
        }
        if (!Utils.isPhoneValid(dto.getPhoneNumber())) {

            return ApiResponse.builder()
                    .isError(true)
                    .message("phone number is not valid")
                    .build();
        }

        Optional<Admins> admins = adminRepository.findAdminsByUsername(dto.getUsername());
        if (admins.isPresent()) {

            return ApiResponse.builder()
                    .isError(true)
                    .message("<< " + admins.get().getUsername() + " >> Already registered")
                    .build();
        }

        Optional<Admins> adminsByEmail = adminRepository.findAdminsByEmail(dto.getEmail());

        if (adminsByEmail.isPresent()) {

            return ApiResponse.builder()
                    .isError(true)
                    .message("<< " + adminsByEmail.get().getEmail() + " >> Already registered")
                    .build();
        }

        if (dto.getCompanyName().isEmpty()){
            return ApiResponse.builder()
                    .isError(true)
                    .message("Company name is empty please enter the company name")
                    .build();
        }

        Admins admin = castToAdmin(dto);

        return ApiResponse.builder()
                .data(admin)
                .isError(false)
                .message("success")
                .build();
    }

    @Override
    public void save(Admins admins) {

        admins.setPassword(passwordEncoder.encode(admins.getPassword()));

        adminRepository.save(admins);
    }

    @Override
    public Admins castToAdmin(RegistrationDto reserve) {
        Admins admins = new Admins();
        try {
            Random random = new Random();

            admins.setPassword(reserve.getPassword());
            admins.setEmail(reserve.getEmail());
            admins.setFirstname(reserve.getFirstname());
            admins.setLastname(reserve.getLastname());
            admins.setOneTimePassword(random.nextInt(8999) + 1000);
            admins.setPhoneNumber(reserve.getPhoneNumber());
            admins.setCreatedTime(new Date());
            admins.setActive(true);
            admins.setUsername(reserve.getUsername());
            admins.setOtpRequestedTime(new Date());
            admins.setActive(false);


            Company company = companyRepository.findByName(reserve.getCompanyName()).get();

            admins.setCompany(company);


            Roles role = new Roles(reserve.getRoleName());

            admins.setRoles(Collections.singletonList(role));
        } catch (ConstraintViolationException c) {
            log.error("castToAdmin ------> username , firstname , lastname , password , email must not be null", c);
        } catch (Exception e) {
            log.error("castToAdmin ------> something strange happened ", e);
        }
        return admins;
    }

    @Override
    public ApiResponse confirm(ConfirmDto dto) {


        Optional<Admins> adminsOptional = adminRepository.findAdminsByEmail(dto.getEmail());
        if (adminsOptional.isEmpty()) {
            return ApiResponse.builder()
                    .isError(true)
                    .message("<< " + adminsOptional.get().getEmail() + ">> User not found ")
                    .build();
        }

        Admins admin = adminsOptional.get();
        if (Objects.equals(admin.getOneTimePassword(), dto.getOtp())) {
            Long diff = new Date().getTime() - admin.getOtpRequestedTime().getTime();
            if (diff <= 120000) {

                admin.setActive(true);
                save(admin);

                return ApiResponse.builder()
                        .isError(false)
                        .data(admin)
                        .build();
            } else {
                return ApiResponse.builder()
                        .isError(true)
                        .message("Otp time out error")
                        .build();
            }

        } else {

            return ApiResponse.builder()
                    .isError(true)
                    .message("Otp is incorrect")
                    .build();        }
    }

}
