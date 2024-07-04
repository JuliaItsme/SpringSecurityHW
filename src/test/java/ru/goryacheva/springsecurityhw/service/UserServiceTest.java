package ru.goryacheva.springsecurityhw.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.goryacheva.springsecurityhw.model.entity.Role;
import ru.goryacheva.springsecurityhw.model.entity.User;
import ru.goryacheva.springsecurityhw.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser() {
        User created = userRepository.save(new User(null, "new", "new@gmail.com","newPass",
                Collections.singleton(Role.USER)));
        String newId = created.getId();
        User newUser = new User(null, "new", "new@gmail.com","newPass",
                Collections.singleton(Role.USER));
        newUser.setId(newId);

    }

    @Test
    void findByUsername() {

    }

    @Test
    void loadUserByUsername() {
    }
}