package shakh.billingsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.ConfirmDto;
import shakh.billingsystem.models.RegistrationDto;
import shakh.billingsystem.repositories.ReserveAdminRepository;
import shakh.billingsystem.services.AdminService;
import shakh.billingsystem.services.RoleService;
import shakh.billingsystem.utilities.JwtTokenCreator;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admins")
public class RegistrationController {

    private final AdminService adminService;
    private final JavaMailSender javaMailSender;
    private final ReserveAdminRepository reserveAdminRepository;
    private final RoleService roleService;


    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationDto dto) {

        ReserveAdmin admin = adminService.castToAdmin(dto);
        reserveAdminRepository.save(admin);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Tasdiqlash kodi");
        mailMessage.setText(admin.getOneTimePassword().toString());
        mailMessage.setTo(admin.getEmail());
        javaMailSender.send(mailMessage);

        return ResponseEntity.status(HttpStatus.CREATED).body(admin.getEmail());
    }

    @PostMapping("/confirmation")
    public ResponseEntity confirmation(@RequestBody ConfirmDto dto){

        ReserveAdmin reserveAdmin = reserveAdminRepository.findReserveAdminByEmail(dto.getEmail());
        if (reserveAdmin == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bunday emaildagi foydalanuvchi mavjud emas");

        if (Objects.equals(reserveAdmin.getOneTimePassword(), dto.getOtp())){
            Long diff =  new Date().getTime() - reserveAdmin.getOtpRequestedTime().getTime();
            if (diff<=120000){

                Admins admins = adminService.castToAdmin(reserveAdmin);
                adminService.save(admins);
                return ResponseEntity.status(HttpStatus.CREATED).body(JwtTokenCreator.createJwtToken(admins));
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("otp time out error");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("otp xato kiritilgan!");
        }
    }


}
