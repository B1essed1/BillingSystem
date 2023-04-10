package shakh.billingsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.CompanyRegDto;
import shakh.billingsystem.models.ConfirmDto;
import shakh.billingsystem.models.RegistrationDto;
import shakh.billingsystem.repositories.ReserveAdminRepository;
import shakh.billingsystem.services.AdminService;
import shakh.billingsystem.services.CompanyService;
import shakh.billingsystem.services.RoleService;
import shakh.billingsystem.utilities.JwtTokenCreator;
import shakh.billingsystem.utilities.Utils;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admins")
public class RegistrationController {

    private final AdminService adminService;
    private final JavaMailSender javaMailSender;
    private final CompanyService companyService;


    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationDto dto) {

        ApiResponse response = adminService.register(dto);
        if (response.getIsError()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        Admins admin = (Admins) response.getData();
        adminService.save(admin);

        Utils.sendOtpToEmail(admin, javaMailSender);

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
    @PreAuthorize("SUPER_ADMIN")
    public ResponseEntity registerCompany(@RequestBody CompanyRegDto reg){

        ApiResponse<?> response = companyService.registerCompany(reg);
        if (response.getIsError())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(response.getMessage());
    }
}
