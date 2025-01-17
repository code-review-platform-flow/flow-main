package com.flow.main.controller;


import com.flow.main.dto.controller.auth.code.request.VerifyCodeRequestDto;
import com.flow.main.dto.controller.auth.code.response.VerifyCodeResponseDto;
import com.flow.main.dto.controller.auth.login.request.LoginRequestDto;
import com.flow.main.dto.controller.auth.login.response.LoginResponseDto;
import com.flow.main.dto.controller.auth.logout.response.LogoutResponseDto;
import com.flow.main.dto.controller.auth.major.response.MajorGetAllResponseDto;
import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.flow.main.dto.controller.auth.email.response.SendEmailResponseDto;
import com.flow.main.dto.controller.auth.file.response.FileUploadResponseDto;
import com.flow.main.dto.controller.auth.register.response.RegisterResponseDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.service.auth.AuthSendVerifyEmailService;
import com.flow.main.service.auth.AuthVerifyCodeService;
import com.flow.main.service.major.MajorGetAllService;
import com.flow.main.service.userinfo.UserInfoFileUploadService;
import com.flow.main.service.userinfo.UserInfoRegisterService;
import com.flow.main.service.usersessions.UserSessionsLoginService;
import com.flow.main.service.usersessions.UserSessionsLogoutService;
import com.flow.main.service.usersessions.UserSessionsUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MajorGetAllService majorGetAllService;
    private final AuthVerifyCodeService authVerifyCodeService;
    private final AuthSendVerifyEmailService authSendVerifyEmailService;
    private final UserSessionsLoginService userSessionsLoginService;
    private final UserInfoRegisterService userInfoRegisterService;
    private final UserSessionsUpdateService userSessionsUpdateService;
    private final UserSessionsLogoutService userSessionsLogoutService;
    private final UserInfoFileUploadService userInfoFileUploadService;

    @GetMapping("/major")
    public ResponseEntity<MajorGetAllResponseDto> getAllMajor(){
        return ResponseEntity.ok(majorGetAllService.getAllMajor());
    }

    @PostMapping("/email")
    public ResponseEntity<SendEmailResponseDto> sendVerifyEmail(@RequestBody final SendEmailRequestDto sendEmailRequestDto){
        log.info("email : {}", sendEmailRequestDto.getEmail());
        log.info("universityName : {}", sendEmailRequestDto.getUniversityName());

        return ResponseEntity.ok(authSendVerifyEmailService.sendVerifyEmail(sendEmailRequestDto));
    }

    @PostMapping("/code")
    public ResponseEntity<VerifyCodeResponseDto> verifyCode(@RequestBody final VerifyCodeRequestDto verifyCodeRequestDto) throws IOException {
        return ResponseEntity.ok(authVerifyCodeService.verifyCode(verifyCodeRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody final RegisterRequestDto registerRequestDto) throws IOException {
        log.info("email : {}",registerRequestDto.getEmail());
        log.info("password : {}",registerRequestDto.getPassword());
        log.info("majorName : {}",registerRequestDto.getMajorName());
        log.info("schoolName : {}",registerRequestDto.getSchoolName());
        log.info("studentNumber : {}",registerRequestDto.getStudentNumber());
        log.info("userName : {}",registerRequestDto.getUserName());
        return ResponseEntity.ok(userInfoRegisterService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto){
        log.info("email : {}", loginRequestDto.getEmail());
        log.info("password : {}", loginRequestDto.getPassword());
        return ResponseEntity.ok(userSessionsLoginService.login(loginRequestDto));
    }

    @PatchMapping("/refresh-token")
    public ResponseEntity<RecreateAccessTokenResponseDto> recreate(@RequestHeader("Authorization") String refreshToken) {
        log.info("refreshToken : {}", refreshToken);

        return ResponseEntity.ok(userSessionsUpdateService.recreateAccessToken(refreshToken));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(@RequestHeader("Authorization") String accessToken) {
        log.info("accessToken : {}", accessToken);
        return ResponseEntity.ok(userSessionsLogoutService.logout(accessToken));
    }

    @PostMapping("/file/upload")
    public ResponseEntity<FileUploadResponseDto> upload(
        @RequestPart("file") MultipartFile file,
        @RequestPart("email") String email){

        log.info("file.getOriginalFileName : {}", file.getOriginalFilename());
        log.info("email : {}", email);

        return ResponseEntity.ok(userInfoFileUploadService.fileUpload(file, email));
    }
}
