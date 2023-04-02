package shakh.billingsystem.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Company;
import shakh.billingsystem.repositories.AdminRepository;
import shakh.billingsystem.repositories.CompanyRepository;
import shakh.billingsystem.utilities.ThreadLocalSingleton;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static shakh.billingsystem.utilities.Constants.SECURITY_KEY;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    private AdminRepository adminRepository;
    private CompanyRepository companyRepository;

    // Setters for dependencies injection
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/login") || request.getServletPath().equals("/api/admins/refresh/token")){
            filterChain.doFilter(request,response);
        } else {
                String authorizationHeader  = request.getHeader(AUTHORIZATION);
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                    try {
                        String token = authorizationHeader.substring("Bearer ".length());
                        Algorithm algorithm = Algorithm.HMAC256(SECURITY_KEY.getBytes());
                        JWTVerifier verifier = JWT.require(algorithm).build();
                        DecodedJWT decodedJWT = verifier.verify(token);
                        Company company = companyRepository.getById(Long.getLong(decodedJWT.getIssuer()));
                        ThreadLocalSingleton.setCompany(company);
                        String username =  decodedJWT.getSubject();
                        Admins admins = adminRepository.findAdminsByUsername(username).get();
                        ThreadLocalSingleton.setAdmin(admins);

                        String [] roles = decodedJWT.getClaim("roles").asArray(String.class);
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        stream(roles).forEach(role ->
                                authorities.add(new SimpleGrantedAuthority(role)));

                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null,authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request,response);
                    } catch (Exception e){
                        log.error("error logging in - -> {}", e.getMessage());
                        response.setHeader("error" , e.getMessage());
                        Map<String, String> error_message = new HashMap<>();
                        error_message.put("error_message", e.getMessage());
                        response.setContentType(APPLICATION_JSON_VALUE);

                        new ObjectMapper().writeValue(response.getOutputStream(), error_message);
                    }
                }else {
                    filterChain.doFilter(request,response);
                }


        }
    }
}
