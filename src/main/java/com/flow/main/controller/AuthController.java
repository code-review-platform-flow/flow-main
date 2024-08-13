package com.flow.main.controller;


import com.flow.main.dto.controller.auth.code.request.VerifyCodeRequestDto;
import com.flow.main.dto.controller.auth.code.response.VerifyCodeResponseDto;
import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.logout.response.LogoutResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.flow.main.dto.controller.auth.email.response.SendEmailResponseDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.service.auth.AuthSendVerifyEmailService;
import com.flow.main.service.auth.AuthVerifyCodeService;
import com.flow.main.service.userinfo.UserInfoRegisterService;
import com.flow.main.service.usersessions.UserSessionsLoginService;
import com.flow.main.service.usersessions.UserSessionsLogoutService;
import com.flow.main.service.usersessions.UserSessionsUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthVerifyCodeService authVerifyCodeService;
    private final AuthSendVerifyEmailService authSendVerifyEmailService;
    private final UserSessionsLoginService userSessionsLoginService;
    private final UserInfoRegisterService userInfoRegisterService;
    private final UserSessionsUpdateService userSessionsUpdateService;
    private final UserSessionsLogoutService userSessionsLogoutService;

    @PostMapping("/email")
    public ResponseEntity<SendEmailResponseDto> sendVerifyEmail(@RequestBody final SendEmailRequestDto sendEmailRequestDto) throws IOException {
        return ResponseEntity.ok(authSendVerifyEmailService.sendVerifyEmail(sendEmailRequestDto));
    }

    @PostMapping("/code")
    public ResponseEntity<VerifyCodeResponseDto> verifyCode(@RequestBody final VerifyCodeRequestDto verifyCodeRequestDto) throws IOException {
        return ResponseEntity.ok(authVerifyCodeService.verifyCode(verifyCodeRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody final RegisterRequestDto registerRequestDto) throws IOException {
        userInfoRegisterService.register(registerRequestDto);
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
