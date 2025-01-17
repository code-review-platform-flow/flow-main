package com.flow.main.service.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsersPasswordVerifyService {

    private final PasswordEncoder passwordEncoder;
    public void verifyPassword(String userPassword, String loginPassword){
        if (!passwordEncoder.matches(loginPassword, userPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
    }
}
