package com.flow.main.service.userinfo;

import com.flow.main.common.property.UserInfoProperty;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.school.SchoolDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.school.persistence.SchoolService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.UsersCreateService;
import com.flow.main.service.users.UsersCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoCreateService {

    private final MajorService majorService;
    private final SchoolService schoolService;
    private final UserInfoService userInfoService;
    private final UsersCreateService usersCreateService;
    private final UsersCheckService usersCheckService;
    private final UserInfoProperty userInfoProperty;

    public UserInfoDto create(RegisterRequestDto registerRequestDto){
        usersCheckService.checkExistUser(registerRequestDto.getEmail());
        UsersDto usersDto = usersCreateService.create(registerRequestDto);
        MajorDto majorDto = majorService.findByMajorName(registerRequestDto.getMajorName());
        SchoolDto schoolDto = schoolService.findBySchoolName(registerRequestDto.getSchoolName());
        UserInfoDto userInfoDto = UserInfoDto.builder()
            .userId(usersDto.getUserId())
            .schoolId(schoolDto.getSchoolId())
            .majorId(majorDto.getMajorId())
            .studentNumber(registerRequestDto.getStudentNumber())
            .role("ROLE_USER")
            .userName(registerRequestDto.getUserName())
            .profileUrl(userInfoProperty.getUrl())
            .build();
        return userInfoService.save(userInfoDto);
    }

}
