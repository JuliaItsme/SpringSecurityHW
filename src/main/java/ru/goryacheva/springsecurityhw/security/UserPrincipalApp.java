package ru.goryacheva.springsecurityhw.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserPrincipalApp implements Principal {

    private final String name;
    private final String email;
    private final List<String> roles;

    @Override
    public String getName() {
        return name;
    }
}
