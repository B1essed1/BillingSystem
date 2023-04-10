package shakh.billingsystem.utilities;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import shakh.billingsystem.entities.Admins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isPhoneValid(String phone) {
        String pattern = "^\\+998[0-9]{2}[0-9]{7}$";
        if (phone == null || !phone.matches(pattern)) {
            return false;
        }
        return true;
    }

    public static Boolean isPasswordValid(String password) {
        /**
         * have at least 8 characters
         * contain at least one letter
         * contain at least one digit
         * contain at least one special character (@, $, !, %, *, #, ?, &)
         * */
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        return password.matches(pattern);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void sendOtpToEmail(Admins admin, JavaMailSender javaMailSender){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Tasdiqlash kodi");
        mailMessage.setText(admin.getOneTimePassword().toString());
        mailMessage.setTo(admin.getEmail());
        javaMailSender.send(mailMessage);
    }

}
