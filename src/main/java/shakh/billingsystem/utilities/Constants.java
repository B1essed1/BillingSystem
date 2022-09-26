package shakh.billingsystem.utilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import shakh.billingsystem.entities.Admins;

@Slf4j
public class Constants {
    public static final String SECURITY_KEY = "b1essed1#billingSystem";
    public static final String USERNAME_NOT_FOUND = " %s not found in database";

    public static Admins getCurrentLoggedInUser(){
        Admins admins = (Admins) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return admins;
    }
}
