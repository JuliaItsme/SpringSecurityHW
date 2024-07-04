package ru.goryacheva.springsecurityhw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goryacheva.springsecurityhw.model.entity.User;
import ru.goryacheva.springsecurityhw.repository.UserRepository;
import ru.goryacheva.springsecurityhw.security.UserDetailsApp;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User: " + username + ", not found!"));
        return new UserDetailsApp(user);
    }
}
