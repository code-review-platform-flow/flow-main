package com.flow.main.controller;


import com.flow.main.dto.login.request.LoginRequestDto;
import com.flow.main.dto.login.response.LoginResponseDto;
import com.flow.main.dto.register.request.RegisterRequestDto;
import com.flow.main.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody final RegisterRequestDto registerRequestDto){
        log.info("Email : {}", registerRequestDto.getEmail());
        log.info("Password : {}", registerRequestDto.getPassword());
        log.info("SchoolName : {}", registerRequestDto.getSchoolName());
        log.info("MajorName : {}", registerRequestDto.getMajorName());

        return authService.registerUser(registerRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto){
        log.info("Email : {}", loginRequestDto.getEmail());
        log.info("Password : {}", loginRequestDto.getPassword());

        return authService.login(loginRequestDto);
    }
}
