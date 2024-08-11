package com.flow.main.controller;


import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.logout.response.LogoutResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.service.userinfo.UserInfoCreateService;
import com.flow.main.service.usersessions.UserSessionsLoginService;
import com.flow.main.service.usersessions.UserSessionsLogoutService;
import com.flow.main.service.usersessions.UserSessionsUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserSessionsLoginService userSessionsLoginService;
    private final UserInfoCreateService userInfoCreateService;
    private final UserSessionsUpdateService userSessionsUpdateService;
    private final UserSessionsLogoutService userSessionsLogoutService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody final RegisterRequestDto registerRequestDto){
        userInfoCreateService.create(registerRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userSessionsLoginService.login(loginRequestDto));
    }

    @PatchMapping("/refresh-token")
    public ResponseEntity<RecreateAccessTokenResponseDto> recreate(@RequestHeader("RefreshToken") String refreshToken) {
        UserSessionsDto userSessionsDto = userSessionsUpdateService.recreateAccessToken(refreshToken);
        return ResponseEntity.ok(RecreateAccessTokenResponseDto.builder()
            .accessToken(userSessionsDto.getAccessToken()).build());
    }

    @DeleteMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(@RequestHeader("AccessToken") String accessToken) {
        return ResponseEntity.ok(userSessionsLogoutService.logout(accessToken));
    }
}
