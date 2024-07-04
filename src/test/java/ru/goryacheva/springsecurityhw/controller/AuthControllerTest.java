package ru.goryacheva.springsecurityhw.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.goryacheva.springsecurityhw.service.SecurityService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthControllerTest {

    @Autowired
    private SecurityService securityService;

    @Test
    void password() {

    }
}