package com.flow.main.controller;


import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.service.AuthService;
import com.flow.main.service.userinfo.UserInfoCreateService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserInfoCreateService userInfoCreateService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody final RegisterRequestDto registerRequestDto){
        log.info("Email : {}", registerRequestDto.getEmail());
        log.info("Password : {}", registerRequestDto.getPassword());
        log.info("SchoolName : {}", registerRequestDto.getSchoolName());
        log.info("MajorName : {}", registerRequestDto.getMajorName());

        userInfoCreateService.create(registerRequestDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto){
        log.info("Email : {}", loginRequestDto.getEmail());
        log.info("Password : {}", loginRequestDto.getPassword());

        return authService.login(loginRequestDto);
    }

    @PatchMapping("/refresh-token")
    public ResponseEntity<RecreateAccessTokenResponseDto> recreate(@RequestHeader("RefreshToken") String refreshToken)
        throws IOException {
        return authService.recreate(refreshToken);
    }
}
