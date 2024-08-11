package com.flow.main.service.usersessions.persistence;

import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.entity.UserSessionsEntity;
import com.flow.main.mapper.UserSessionsMapper;
import com.flow.main.mapper.UsersMapper;
import com.flow.main.repository.UserSessionsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSessionsService {

    private final UserSessionsRepository userSessionsRepository;
    private final UserSessionsMapper userSessionsMapper;

    @Transactional
    public UserSessionsDto save(UserSessionsDto userSessionsDto){
        UserSessionsEntity userSessionsEntity = userSessionsMapper.toEntity(userSessionsDto);
        return userSessionsMapper.toDto(userSessionsRepository.save(userSessionsEntity));
    }

    @Transactional(readOnly = true)
    public UserSessionsDto findOrEmptyUserSessionsByUserId(Long userId){
        return userSessionsMapper.toDto(userSessionsRepository.findByUserId(userId)
                .orElse(new UserSessionsEntity()));
    }

    @Transactional(readOnly = true)
    public UserSessionsDto findByRefreshToken(String refreshToken){
        return userSessionsMapper.toDto(userSessionsRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new EntityNotFoundException("UserSessions not found with refreshToken : " + refreshToken)));
    }

    @Transactional(readOnly = true)
    public UserSessionsDto findByAccessToken(String accessToken){
        return userSessionsMapper.toDto(userSessionsRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new EntityNotFoundException("UserSessions not found with accessToken : " + accessToken)));
    }

    @Transactional
    public UserSessionsDto delete(UserSessionsDto userSessionsDto){
        UserSessionsEntity userSessionsEntity = userSessionsMapper.toEntity(userSessionsDto);
        userSessionsEntity.markDeleted();
        return userSessionsMapper.toDto(userSessionsRepository.save(userSessionsEntity));
    }
}
