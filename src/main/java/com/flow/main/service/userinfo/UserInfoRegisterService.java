package com.flow.main.service.userinfo;

import com.flow.main.common.property.UserInfoProperty;
import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.controller.auth.register.response.RegisterResponseDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.school.SchoolDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.auth.AuthCheckVerifiedEmailService;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.school.persistence.SchoolService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.UsersRegisterService;
import com.flow.main.service.users.UsersCheckService;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserInfoRegisterService {

    private final AuthCheckVerifiedEmailService authCheckVerifiedEmailService;
    private final MajorService majorService;
    private final SchoolService schoolService;
    private final UserInfoService userInfoService;
    private final UsersRegisterService usersRegisterService;
    private final UsersCheckService usersCheckService;
    private final UserInfoProperty userInfoProperty;

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) throws IOException {
        authCheckVerifiedEmailService.checkVerifiedEmail(registerRequestDto.getEmail());
        usersCheckService.checkExistUser(registerRequestDto.getEmail());
        UsersDto usersDto = usersRegisterService.register(registerRequestDto);
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

        userInfoService.save(userInfoDto);

        return RegisterResponseDto.builder().build();
    }

}
