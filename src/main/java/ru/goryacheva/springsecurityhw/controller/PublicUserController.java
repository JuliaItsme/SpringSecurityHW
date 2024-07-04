package ru.goryacheva.springsecurityhw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goryacheva.springsecurityhw.exception.AuthException;
import ru.goryacheva.springsecurityhw.model.dto.UserRequest;
import ru.goryacheva.springsecurityhw.model.entity.Role;
import ru.goryacheva.springsecurityhw.model.entity.User;
import ru.goryacheva.springsecurityhw.service.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/public/user")
@RequiredArgsConstructor
@Tag(name = "Регистрация")
public class PublicUserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Проверка и регистрация пользователя")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {

        if (!request.getPassword().equals(request.getDoublePassword())) {
            return new ResponseEntity<>(new AuthException(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AuthException(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
         User user = userService.createUser(User.builder()
                 .email(request.getEmail())
                 .password(request.getPassword())
                 .username(request.getUsername())
                 .roles(request.getRoles().stream().map(role -> Role.valueOf(role.toUpperCase()))
                         .collect(Collectors.toSet())).build());
        return  ResponseEntity.ok("Пользователь создан");
    }
}
