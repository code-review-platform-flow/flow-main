package com.flow.main.service.likes.persistence;

import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.entity.LikesEntity;
import com.flow.main.mapper.LikesMapper;
import com.flow.main.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final LikesMapper likesMapper;

    @Transactional
    public LikesDto save(LikesDto likesDto){
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        return likesMapper.toDto(likesRepository.save(likesEntity));
    }

    @Transactional(readOnly = true)
    public LikesDto findOrCreateByUserIdAndPostId(Long userId, Long postId){
        return likesMapper.toDto(likesRepository.findByUserIdAndPostId(userId, postId)
            .orElse(LikesEntity.builder().build()));
    }

    @Transactional
    public LikesDto reuse(LikesDto likesDto){
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        likesEntity.markReuse();
        return likesMapper.toDto(likesRepository.save(likesEntity));
    }
}
