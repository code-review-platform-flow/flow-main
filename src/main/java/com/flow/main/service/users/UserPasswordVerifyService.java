package com.flow.main.service.users;

import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPasswordVerifyService {

    private final UsersService usersService;
    public void verifyPassword(String userPassword, String loginPassword){

    }

}
