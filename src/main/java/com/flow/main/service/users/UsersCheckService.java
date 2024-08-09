package com.flow.main.service.users;

import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsersCheckService {

    private final UsersService usersService;

    public void checkExistUser(String email){
        if (usersService.existsByEmail(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with email: " + email);
        }
    }

}
