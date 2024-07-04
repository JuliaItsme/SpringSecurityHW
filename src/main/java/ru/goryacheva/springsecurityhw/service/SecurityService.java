package ru.goryacheva.springsecurityhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.goryacheva.springsecurityhw.model.dto.TokenData;
import ru.goryacheva.springsecurityhw.model.entity.User;
import ru.goryacheva.springsecurityhw.exception.AuthException;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public TokenData processPasswordToken(String username, String password) {
        User user = userService.findByUsername(username).get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthException("Exception trying to check password for user: " + username);
        }

        String token = tokenService.generateToken(user);
        return new TokenData(token);
    }
}