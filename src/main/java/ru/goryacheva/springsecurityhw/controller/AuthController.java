package ru.goryacheva.springsecurityhw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goryacheva.springsecurityhw.model.dto.TokenRequest;
import ru.goryacheva.springsecurityhw.model.dto.TokenData;
import ru.goryacheva.springsecurityhw.model.dto.TokenResponse;
import ru.goryacheva.springsecurityhw.service.SecurityService;

@RestController
@RequestMapping("/api/v1/public/token")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    @Operation(summary = "Проверка и аутентификация пользователя")
    public ResponseEntity<TokenResponse> password(@RequestBody TokenRequest tokenRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(),
                    tokenRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        TokenData tokenData = securityService.processPasswordToken(tokenRequest.getUsername(),
                tokenRequest.getPassword());
        return ResponseEntity.ok(new TokenResponse(tokenData.getToken()));
    }
}

