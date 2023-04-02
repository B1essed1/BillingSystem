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
import shakh.billingsystem.models.ApiResponse;
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


    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationDto dto) {

        ApiResponse response = adminService.register(dto);
        if (response.getIsError()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        Admins admin = (Admins) response.getData();
        adminService.save(admin);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Tasdiqlash kodi");
        mailMessage.setText(admin.getOneTimePassword().toString());
        mailMessage.setTo(admin.getEmail());
        javaMailSender.send(mailMessage);

        return ResponseEntity.status(HttpStatus.CREATED).body(admin.getEmail());
    }

    @PostMapping("/confirmation")
    public ResponseEntity confirmation(@RequestBody ConfirmDto dto){

        ApiResponse response = adminService.confirm(dto);

        if (response.getIsError()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        Admins admins = (Admins) response.getData();
        return ResponseEntity.ok().body(JwtTokenCreator.createJwtToken(admins));
    }

    @PostMapping("add/company")
    public ResponseEntity registerCompany(){

        /**TODO
        * company registration should be implemented
        */

        return ResponseEntity.ok().body("");
    }

}
