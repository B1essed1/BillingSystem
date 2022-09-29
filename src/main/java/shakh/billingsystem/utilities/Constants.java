package shakh.billingsystem.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.repositories.AdminRepository;

import javax.annotation.PostConstruct;

@Slf4j

public class Constants {
    private static AdminRepository adminRepository;

    /*@Autowired
    AdminRepository adminRepo;
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository){
        Constants.adminRepository = adminRepository;
    }


    @PostConstruct
    public void init(){
        Constants.adminRepository = adminRepo;
    }

    public static Admins getCurrentLoggedInUser(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Admins  admins = adminRepository.findAdminsByUsername(name).get();
        return admins;
    }*/
    public static final String SECURITY_KEY = "b1essed1#billingSystem";
    public static final String USERNAME_NOT_FOUND = " %s not found in database";

}
