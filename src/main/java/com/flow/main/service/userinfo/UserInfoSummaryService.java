package com.flow.main.service.userinfo;

import com.flow.main.dto.controller.user.get.summary.request.UserSummaryRequestDto;
import com.flow.main.dto.controller.user.get.summary.response.UserSummaryResponseDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoSummaryService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final MajorService majorService;

    public UserSummaryResponseDto getUserSummary(String email){
        UsersDto usersDto = usersService.findByEmail(email);
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());
        MajorDto majorDto = majorService.findByMajorId(userInfoDto.getMajorId());

        return UserSummaryResponseDto.builder()
            .email(usersDto.getEmail())
            .profileUrl(userInfoDto.getProfileUrl())
            .userName(userInfoDto.getUserName())
            .studentNumber(userInfoDto.getStudentNumber())
            .majorName(majorDto.getMajorName())
            .build();
    }

}
