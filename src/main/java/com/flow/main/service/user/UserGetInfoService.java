package com.flow.main.service.user;

import com.flow.main.dto.controller.user.get.request.UserGetInfoRequestDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGetInfoService {

    private final UsersService usersService;

    public void getUserInfo(String userInfoEmail, UserGetInfoRequestDto userGetInfoRequestDto){

        UsersDto usersDto = usersService.findByEmail(userInfoEmail);


    }

}
