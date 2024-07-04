package ru.goryacheva.springsecurityhw.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.goryacheva.springsecurityhw.exception.AuthException;
import ru.goryacheva.springsecurityhw.service.UserDetailsService;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserPrincipalApp principal = (UserPrincipalApp) authentication.getPrincipal();

        UserDetails user = userDetailsService.loadUserByUsername(principal.getName());
        if(!user.isEnabled()){
            throw new AuthException("User disabled");
        }
        return Stream.of(user).map(userDetails -> authentication).findFirst().get();
    }
}
