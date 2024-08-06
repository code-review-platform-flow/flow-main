package com.flow.main.service.userinfo.persistence;

import com.flow.main.dto.major.MajorDto;
import com.flow.main.dto.school.SchoolDto;
import com.flow.main.dto.userinfo.UserInfoDto;
import com.flow.main.dto.users.UsersDto;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.entity.UsersEntity;
import com.flow.main.mapper.MajorMapper;
import com.flow.main.mapper.SchoolMapper;
import com.flow.main.mapper.UserInfoMapper;
import com.flow.main.mapper.UsersMapper;
import com.flow.main.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    private final UsersMapper usersMapper;
    private final SchoolMapper schoolMapper;
    private final MajorMapper majorMapper;

    @Transactional
    public UserInfoDto registerSave(UserInfoDto userInfoDto, UsersDto usersDto, SchoolDto schoolDto, MajorDto majorDto){
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(userInfoDto);
        userInfoEntity.setUser(usersMapper.toEntity(usersDto));
        userInfoEntity.setSchool(schoolMapper.toEntity(schoolDto));
        userInfoEntity.setMajor(majorMapper.toEntity(majorDto));
        return userInfoMapper.toDto(userInfoRepository.save(userInfoEntity));
    }

    public UserInfoDto findByUserId(Long userId){
        return userInfoMapper.toDto(userInfoRepository.findByUserId(userId).orElseThrow(
            () -> new EntityNotFoundException("UserInfo not found with userI : " + userId)
        ));
    }

}
