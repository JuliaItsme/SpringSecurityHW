package ru.goryacheva.springsecurityhw.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.goryacheva.springsecurityhw.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PublicUserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {

    }
}