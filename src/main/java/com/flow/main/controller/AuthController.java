package com.flow.main.controller;


import com.flow.main.dto.request.RegisterRequestDto;
import com.flow.main.dto.request.RegisterRequestDto;
import com.flow.main.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto registerRequestDto){
        log.info("RegisterDto : {}", registerRequestDto);
        return authService.registerUser(registerRequestDto);
    }
}
