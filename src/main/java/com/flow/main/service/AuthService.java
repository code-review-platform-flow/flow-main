package com.flow.main.service;


import com.flow.main.common.property.JwtProperty;
import com.flow.main.dto.login.request.LoginRequestDto;
import com.flow.main.dto.login.response.LoginResponseDto;
import com.flow.main.dto.major.MajorDto;
import com.flow.main.dto.register.request.RegisterRequestDto;
import com.flow.main.dto.school.SchoolDto;
import com.flow.main.dto.userinfo.UserInfoDto;
import com.flow.main.dto.users.UsersDto;
import com.flow.main.dto.usersessions.UserSessionsDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UsersEntity;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.mapper.RegisterMapper;
import com.flow.main.repository.MajorRepository;
import com.flow.main.repository.SchoolRepository;
import com.flow.main.repository.UserInfoRepository;
import com.flow.main.repository.UsersRepository;

import com.flow.main.security.JwtUtil;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.school.persistence.SchoolService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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


    @Transactional
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
                .role("ROLE_USER")
                .build();

            userInfoService.registerSave(userInfoDto, savedUsers, schoolDto, majorDto);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
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

        UserSessionsDto userSessionsDto;
        boolean check = userSessionsService.existsByUserId(userId);
        if(!check){
            userSessionsDto = UserSessionsDto.builder()
                .userId(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000))
                .build();
        } else {
            userSessionsDto = userSessionsService.findByUserId(userId);
            userSessionsDto.setAccessToken(accessToken);
            userSessionsDto.setRefreshToken(refreshToken);
            userSessionsDto.setExpiration(LocalDateTime.now().plusSeconds(jwtProperty.getAccess().getExpiration() / 1000));
        }

        try{
            userSessionsService.loginSave(userSessionsDto, usersDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(loginResponseDto);
    }
}
