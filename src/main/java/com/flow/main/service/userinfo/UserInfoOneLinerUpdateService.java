package com.flow.main.service.userinfo;

import com.flow.main.dto.controller.user.update.oneliner.request.UserOneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.UserOneLinerUpdateResponseDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoOneLinerUpdateService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;

    public UserOneLinerUpdateResponseDto updateOneLiner(UserOneLinerUpdateRequestDto userOneLinerUpdateRequestDto){

        UsersDto usersDto = usersService.findByEmail(userOneLinerUpdateRequestDto.getEmail());

        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
        userInfoDto.setOneLiner(userOneLinerUpdateRequestDto.getOneLiner());

        UserInfoDto savedUserInfoDto = userInfoService.save(userInfoDto);

        return UserOneLinerUpdateResponseDto.builder()
            .oneLiner(savedUserInfoDto.getOneLiner())
            .build();
    }
}
