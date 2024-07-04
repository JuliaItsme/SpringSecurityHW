package ru.goryacheva.springsecurityhw.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ru.goryacheva.springsecurityhw.model.entity.User;
import ru.goryacheva.springsecurityhw.security.UserPrincipalApp;

import java.security.Principal;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String ROLE_CLAIM = "role";

    private static final String EMAIL_CLAIM = "email";

    @Value("${jwt.secretKey}")
    private String jwtSecret;

    @Value("${jwt.tokenLifetime}")
    private Duration tokenLifetime;

    public String generateToken(User user) {
        List<String> roles = user.getRoles().stream().map(Enum::toString).toList();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + tokenLifetime.toMillis()))
                .claim(ROLE_CLAIM, roles)
                .claim(EMAIL_CLAIM, user.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Authentication toAuthentication(String token) {
        Claims tokenBody = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String subject = tokenBody.getSubject();
        String email = tokenBody.get(EMAIL_CLAIM, String.class);
        List<String> roles = tokenBody.get(ROLE_CLAIM, List.class);

        Principal principal = new UserPrincipalApp(subject, email, roles);
        return new UsernamePasswordAuthenticationToken(principal, null,
                roles.stream().map(SimpleGrantedAuthority::new).toList());
    }
}
