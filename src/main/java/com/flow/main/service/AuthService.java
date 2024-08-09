package com.flow.main.service;


import com.flow.main.common.property.JwtProperty;
import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;

import com.flow.main.service.security.JwtUtil;
import com.flow.main.service.userinfo.UserInfoCreateService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.UsersCreateService;
import com.flow.main.service.users.UsersCheckService;
import com.flow.main.service.users.persistence.UsersService;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final UserSessionsService userSessionsService;
    private final JwtUtil jwtUtil;
    private final JwtProperty jwtProperty;

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto){
        UsersDto usersDto = usersService.findByEmail(loginRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        Long userId = usersDto.getUserId();
        String email = usersDto.getEmail();
        String role = userInfoDto.getRole();

        String accessToken = jwtUtil.createAccessToken(userId, email, role);
        String refreshToken = jwtUtil.createRefreshToken(userId, email, role);

        UserSessionsDto userSessionsDto = userSessionsService.existsByUserId(userId);
        userSessionsDto.setUserId(userId);
        userSessionsDto.setAccessToken(accessToken);
        userSessionsDto.setRefreshToken(refreshToken);
        userSessionsDto.setExpiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000));

        userSessionsService.save(userSessionsDto);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
            .email(email)
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();

        return ResponseEntity.ok(loginResponseDto);
    }
}
