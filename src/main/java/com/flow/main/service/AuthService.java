package com.flow.main.service;


import com.flow.main.dto.request.RegisterRequestDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserEntity;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.mapper.RegisterMapper;
import com.flow.main.repository.MajorRepository;
import com.flow.main.repository.SchoolRepository;
import com.flow.main.repository.UserInfoRepository;
import com.flow.main.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final SchoolRepository schoolRepository;
    private final MajorRepository majorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegisterMapper registerMapper;

    @Transactional
    public ResponseEntity<Void> registerUser(RegisterRequestDto registerRequestDto){

        registerRequestDto.setPassword(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()));

        if(userRepository.existsByEmail(registerRequestDto.getEmail())){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        SchoolEntity schoolEntity = schoolRepository.findBySchoolName(registerRequestDto.getSchoolName()).orElse(null);
        if(schoolEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MajorEntity majorEntity = majorRepository.findByMajorName(registerRequestDto.getMajorName()).orElse(null);
        if(majorEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = registerMapper.toUserEntity(registerRequestDto);
        UserInfoEntity userInfoEntity = registerMapper.toUserInfoEntity(userEntity, schoolEntity, majorEntity, registerRequestDto);
        userInfoEntity.setRole("ROLE_USER");

        try{
            userRepository.save(userEntity);
            userInfoRepository.save(userInfoEntity);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
