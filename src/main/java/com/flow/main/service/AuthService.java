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
    public ResponseEntity<Void> registerUser(RegisterDto registerDto){

        String email = registerDto.getEmail();
        registerDto.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        String schoolName = registerDto.getSchoolName();
        String majorName = registerDto.getMajorName();

        if(userRepository.existsByEmail(email)){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        SchoolEntity schoolEntity = findSchoolEntity(schoolName);
        if(schoolEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MajorEntity majorEntity = findMajorEntity(majorName);
        if(majorEntity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = registerMapper.toUserEntity(registerDto);
        UserInfoEntity userInfoEntity = registerMapper.toUserInfoEntity(userEntity, schoolEntity, majorEntity, registerDto);

        try {
            userRepository.save(userEntity);
            userInfoRepository.save(userInfoEntity);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public SchoolEntity findSchoolEntity(String schoolName){
        Optional<SchoolEntity> schoolEntity = schoolRepository.findBySchoolName(schoolName);
        return schoolEntity.orElse(null);
    }

    public MajorEntity findMajorEntity(String majorName){
        Optional<MajorEntity> majorEntity = majorRepository.findByMajorName(majorName);
        return majorEntity.orElse(null);
    }
}
