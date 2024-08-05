package com.flow.main.service.usersessions.persistence;

import com.flow.main.dto.usersessions.UserSessionsDto;
import com.flow.main.entity.UserSessionsEntity;
import com.flow.main.mapper.UserSessionsMapper;
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
        return userSessionsMapper.toDto(userSessionsEntity);
    }

    public boolean existsByUserId(Long userId){
        return userSessionsRepository.existsByUserId(userId);
    }

    public UserSessionsDto findByUserId(Long userId){
        return userSessionsMapper.toDto(userSessionsRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("UserSessions not found with userId : " + userId)));
    }
}
