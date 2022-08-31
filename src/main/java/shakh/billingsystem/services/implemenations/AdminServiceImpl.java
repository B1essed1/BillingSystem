package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.RegistrationDto;
import shakh.billingsystem.repositories.AdminRepository;
import shakh.billingsystem.repositories.ReserveAdminRepository;
import shakh.billingsystem.repositories.RoleRepository;
import shakh.billingsystem.services.AdminService;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService, UserDetailsService {


    private final AdminRepository adminRepository;
    private final ReserveAdminRepository reserveAdminRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admins admin = adminRepository.findAdminsByUsername(username);
        if (admin == null) {
            log.error("Admin not  found in database");
            throw new UsernameNotFoundException("Admin not found in Db");
        } else {
            log.info("user found in database {}", admin);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        admin.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName()))
        );

        return new User(admin.getUsername(), admin.getPassword(), authorities);
    }

    @Override
    public ReserveAdmin castToAdmin(RegistrationDto dto)  {
        ReserveAdmin reserveAdmin = new ReserveAdmin();
        Optional<ReserveAdmin> check = Optional.ofNullable(reserveAdminRepository.findReserveAdminByUsername(dto.getUsername()));
        if (check.isPresent()) reserveAdmin = check.get();
        try {
            Random random = new Random();
            reserveAdmin.setIsDeleted(false);
            reserveAdmin.setUsername(dto.getUsername());
            reserveAdmin.setCreatedTime(new Date());
            reserveAdmin.setEmail(dto.getEmail());
            reserveAdmin.setPassword(dto.getPassword());
            reserveAdmin.setFirstName(dto.getFirstname());
            reserveAdmin.setLastName(dto.getLastname());
            reserveAdmin.setOtpRequestedTime(new Date());
            reserveAdmin.setOneTimePassword((random.nextInt(8999)+1000));
            reserveAdmin.setRoleName(dto.getRoleName());

            reserveAdmin.setPhoneNumber(reserveAdmin.getPhoneNumber());

        } catch (ConstraintViolationException c) {
            log.error("castToAdmin ------> username , firstname , lastname , password , email must not be null", c);
        } catch (Exception e){
            log.error("castToAdmin ------> something strange happened ", e);
        }
        return reserveAdmin;
    }

    @Override
    public void save(Admins admins) {

        admins.setPassword(passwordEncoder.encode(admins.getPassword()));

        adminRepository.save(admins);
    }

    @Override
    public Admins castToAdmin(ReserveAdmin reserve) {
        Admins admins = new Admins();
        admins.setPassword(reserve.getPassword());
        admins.setEmail(reserve.getEmail());
        admins.setFirstname(reserve.getFirstName());
        admins.setLastname(reserve.getLastName());
        admins.setOneTimePassword(reserve.getOneTimePassword());
        admins.setPhoneNumber(reserve.getPhoneNumber());
        admins.setCreatedTime(new Date());
        admins.setActive(true);
        admins.setUsername(reserve.getUsername());
        admins.setOtpRequestedTime(reserve.getOtpRequestedTime());

        if (roleRepository.findRolesByName(reserve.getRoleName()).isEmpty()){
            Roles role = new Roles();
            role.setName(reserve.getRoleName());
            roleRepository.save(role);
            admins.setRoles(Collections.singletonList(role));
        } else {
            Roles role = roleRepository.findRolesByName(reserve.getRoleName()).get();
            admins.setRoles(Collections.singletonList(role));
        }
        return admins;
    }
}
