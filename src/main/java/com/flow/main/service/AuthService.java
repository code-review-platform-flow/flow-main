package com.flow.main.service;


import com.flow.main.common.property.JwtProperty;
import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.jpa.school.SchoolDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;

import com.flow.main.security.JwtUtil;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.school.persistence.SchoolService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final MajorService majorService;
    private final SchoolService schoolService;
    private final UserSessionsService userSessionsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;
    private final JwtProperty jwtProperty;

    public ResponseEntity<Void> registerUser(RegisterRequestDto registerRequestDto){
        registerRequestDto.setPassword(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()));

        if(usersService.existsByEmail(registerRequestDto.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        MajorDto majorDto = majorService.findByMajorName(registerRequestDto.getMajorName());
        SchoolDto schoolDto = schoolService.findBySchoolName(registerRequestDto.getSchoolName());

        try{
            UsersDto usersDto = UsersDto.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .build();

            UsersDto savedUsers = usersService.save(usersDto);

            UserInfoDto userInfoDto = UserInfoDto.builder()
                .studentNumber(registerRequestDto.getStudentNumber())
                .majorId(majorDto.getMajorId())
                .schoolId(schoolDto.getSchoolId())
                .userId(savedUsers.getUserId())
                .role("ROLE_USER")
                .build();

            userInfoService.save(userInfoDto);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto){
        UsersDto usersDto = usersService.findByEmail(loginRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        Long userId = usersDto.getUserId();
        String email = usersDto.getEmail();
        String role = userInfoDto.getRole();

        String accessToken = jwtUtil.createAccessToken(userId, email, role);
        String refreshToken = jwtUtil.createRefreshToken(userId, email, role);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
            .email(email)
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();

        UserSessionsDto userSessionsDto = userSessionsService.existsByUserId(userId);
        userSessionsDto.setUserId(userId);
        userSessionsDto.setAccessToken(accessToken);
        userSessionsDto.setRefreshToken(refreshToken);
        userSessionsDto.setExpiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000));

        try{
            userSessionsService.save(userSessionsDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(loginResponseDto);
    }

    public ResponseEntity<RecreateAccessTokenResponseDto> recreate(String refreshToken)
        throws IOException {
        if (refreshToken.isEmpty() || !refreshToken.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtUtil.getToken(refreshToken);
        boolean checkValid = jwtUtil.isExpired(token);
        if (checkValid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserSessionsDto userSessionsDto = userSessionsService.findByRefreshToken(token);
        Long userId = jwtUtil.getUserId(token);
        String email = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);
        String accessToken = jwtUtil.createAccessToken(userId, email, role);

        userSessionsDto.setAccessToken(accessToken);

        userSessionsService.save(userSessionsDto);
        RecreateAccessTokenResponseDto recreateAccessTokenResponseDto = RecreateAccessTokenResponseDto.builder()
            .accessToken(accessToken).build();
        return ResponseEntity.ok(recreateAccessTokenResponseDto);
    }
}
