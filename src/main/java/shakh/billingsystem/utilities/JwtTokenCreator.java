package shakh.billingsystem.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Roles;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static shakh.billingsystem.utilities.Constants.SECURITY_KEY;

public class JwtTokenCreator {
    public  static Map<String,String> createJwtToken(Admins admins){
        Map<String , String> tokens = new HashMap<>();
        Algorithm algorithm = Algorithm.HMAC256(SECURITY_KEY);

        String access_token = JWT.create()
                .withSubject(admins.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 24 * 7))
                .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                .withClaim("roles",  admins.getRoles().stream().map(Roles::getName).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(admins.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 60 * 24 * 30))
                .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                .sign(algorithm);

        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        return tokens;
    }



    public  static Map<String,String> createJwtToken(User admins){
        Map<String , String> tokens = new HashMap<>();
        Algorithm algorithm = Algorithm.HMAC256(SECURITY_KEY);

        String access_token = JWT.create()
                .withSubject(admins.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 24 * 7))
                .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                .withClaim("roles", admins.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(admins.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 60 * 24 * 30))
                .withIssuer(String.valueOf(admins.getUsername().hashCode()))
                .sign(algorithm);

        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        return tokens;
    }
}
