package com.flow.main.service.userinfo;

import com.flow.main.dto.controller.user.update.oneliner.request.OneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.OneLinerUpdateResponseDto;
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

    public OneLinerUpdateResponseDto updateOneLiner(
        OneLinerUpdateRequestDto oneLinerUpdateRequestDto){

        UsersDto usersDto = usersService.findByEmail(oneLinerUpdateRequestDto.getEmail());

        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
        userInfoDto.setOneLiner(oneLinerUpdateRequestDto.getOneLiner());

        UserInfoDto savedUserInfoDto = userInfoService.save(userInfoDto);

        return OneLinerUpdateResponseDto.builder()
            .userInfoId(savedUserInfoDto.getUserInfoId())
            .oneLiner(savedUserInfoDto.getOneLiner())
            .build();
    }
}
