package com.flow.main.service.follows.persistence;

import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.entity.FollowsEntity;
import com.flow.main.mapper.FollowsMapper;
import com.flow.main.repository.FollowsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FollowsService {

    private final FollowsRepository followsRepository;
    private final FollowsMapper followsMapper;

    @Transactional
    public FollowsDto save(FollowsDto followsDto){
        FollowsEntity followsEntity = followsMapper.toEntity(followsDto);
        return followsMapper.toDto(followsRepository.save(followsEntity));
    }

    @Transactional(readOnly = true)
    public void checkAlreadyFollowed(Long followerId, Long followeeId) {
        followsRepository.findByFollowerIdAndFolloweeIdUseYnTrue(
            followerId, followeeId)
            .ifPresent(followsEntity -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already followed");
            });
    }

    @Transactional(readOnly = true)
    public boolean existsByFolloweeIdAndFollowerId(Long followeeId, Long followerId){
        return followsRepository.existsByFolloweeIdAndFollowerId(followeeId, followerId);
    }

    @Transactional(readOnly = true)
    public FollowsDto findOrEmptyByFollowerIdAndFolloweeIdUseYnTrue(Long followerId, Long followeeId){
        return followsMapper.toDto(followsRepository.findByFollowerIdAndFolloweeIdUseYnTrue(followerId, followeeId)
            .orElseThrow(() -> new EntityNotFoundException("Follows not found with followerId : " + followerId + ", followeeId : " + followeeId)));
    }

    @Transactional(readOnly = true)
    public FollowsDto findOrEmptyByFollowerIdAndFolloweeIdUseYnFalse(Long followerId, Long followeeId){
        return followsMapper.toDto(followsRepository.findByFollowerIdAndFolloweeIdUseYnFalse(followerId, followeeId)
            .orElse(new FollowsEntity()));
    }

    @Transactional(readOnly = true)
    public Long countByFolloweeId(Long followeeId){
        return followsRepository.countByFolloweeId(followeeId);
    }

    @Transactional
    public FollowsDto reuse(FollowsDto followsDto){
        FollowsEntity followsEntity = followsMapper.toEntity(followsDto);
        followsEntity.markReuse();
        return followsMapper.toDto(followsRepository.save(followsEntity));
    }

    @Transactional
    public FollowsDto delete(FollowsDto followsDto){
        FollowsEntity followsEntity = followsMapper.toEntity(followsDto);
        followsEntity.markDeleted();
        return followsMapper.toDto(followsRepository.save(followsEntity));
    }
}
