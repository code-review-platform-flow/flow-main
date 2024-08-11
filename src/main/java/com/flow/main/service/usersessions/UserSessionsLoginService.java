package com.flow.main.service.usersessions;

import com.flow.main.common.property.JwtProperty;
import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.service.security.JwtUtil;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.UserPasswordVerifyService;
import com.flow.main.service.users.persistence.UsersService;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserSessionsLoginService {

    private final UsersService usersService;
    private final UserPasswordVerifyService userPasswordVerifyService;
    private final UserInfoService userInfoService;
    private final UserSessionsService userSessionsService;
    private final JwtUtil jwtUtil;
    private final JwtProperty jwtProperty;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        /*
        * 1. 회원가입된 사용자여야 함
        * 2. DB의 이메일과 비밀번호가 일치하는 지 확인
        * 3. 처음 로그인하는 경우, 다시 로그인 하는 경우로 나누기
        *    - 처음 로그인 시 : UserSessions 생성
        *    - 다시 로그인 시 : false 처리된 userSessions를 찾아서 true 및 accessToken, refreshToken 생성해주기
        * */

        UsersDto usersDto = usersService.findByEmail(loginRequestDto.getEmail());
        userPasswordVerifyService.verifyPassword(usersDto.getPassword(), loginRequestDto.getPassword());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        Long userId = userInfoDto.getUserId();
        String email = usersDto.getEmail();
        String role = userInfoDto.getRole();

        String accessToken = jwtUtil.createAccessToken(userId, email, role);
        String refreshToken = jwtUtil.createRefreshToken(userId, email, role);

        UserSessionsDto userSessionsDto = userSessionsService.findOrEmptyUserSessionsByUserId(usersDto.getUserId());
        userSessionsDto.setUserId(userId);
        userSessionsDto.setAccessToken(accessToken);
        userSessionsDto.setRefreshToken(refreshToken);
        userSessionsDto.setExpiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000));

        userSessionsService.save(userSessionsDto);

        return LoginResponseDto.builder()
                .email(email)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
