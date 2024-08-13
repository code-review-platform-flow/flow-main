package com.flow.main.service.userinfo.persistence;

import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.mapper.UserInfoMapper;
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

    @Transactional
    public UserInfoDto save(UserInfoDto userInfoDto){
        UserInfoEntity userInfoEntity = userInfoMapper.toEntity(userInfoDto);
        return userInfoMapper.toDto(userInfoRepository.save(userInfoEntity));
    }

    @Transactional(readOnly = true)
    public UserInfoDto findByUserId(Long userId){
        return userInfoMapper.toDto(userInfoRepository.findByUserId(userId).orElseThrow(
            () -> new EntityNotFoundException("UserInfo not found with userId : " + userId)
        ));
    }

}
