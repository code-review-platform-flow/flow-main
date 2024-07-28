package com.flow.main.service;


import com.flow.main.dto.request.RegisterDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserEntity;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.mapper.RegisterMapper;
import com.flow.main.repository.MajorRepository;
import com.flow.main.repository.SchoolRepository;
import com.flow.main.repository.UserInfoRepository;
import com.flow.main.repository.UserRepository;

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
    private final RegisterMapper registerMapper = RegisterMapper.INSTANCE;

    @Transactional
    public ResponseEntity<Void> registerUser(RegisterDto registerDto){

        registerDto.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));

        if(userRepository.existsByEmail(registerDto.getEmail())){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UserEntity userEntity = registerMapper.registerDtoToUserEntity(registerDto);
        SchoolEntity schoolEntity = registerMapper.registerDtoToSchoolEntity(registerDto);
        MajorEntity majorEntity = registerMapper.registerDtoToMajorEntity(registerDto);
        UserInfoEntity userInfoEntity = registerMapper.registerDtoToUserInfoEntity(userEntity, schoolEntity, majorEntity, registerDto);

        try {
            userRepository.save(userEntity);
            schoolRepository.save(schoolEntity);
            majorRepository.save(majorEntity);
            userInfoRepository.save(userInfoEntity);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
