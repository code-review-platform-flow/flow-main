package com.flow.main.service.likes.persistence;

import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.LikesEntity;
import com.flow.main.entity.PostsEntity;
import com.flow.main.mapper.LikesMapper;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.repository.LikesRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final LikesMapper likesMapper;
    private final PostsMapper postsMapper;

    @Transactional
    public LikesDto save(LikesDto likesDto){
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        return likesMapper.toDto(likesRepository.save(likesEntity));
    }

    @Transactional(readOnly = true)
    public List<PostsDto> findPostsOrderByLikeCount(Pageable pageable){
        return postsMapper.toDtoList(likesRepository.findPostIdsOrderByLikeCount(pageable)
            .orElse(Collections.emptyList()));
    }

    @Transactional(readOnly = true)
    public void checkUserAlreadyClickLike(Long userId, Long postId, Boolean useYn){
        LikesEntity likesEntity = likesRepository.findByUserIdAndPostId(userId, postId, useYn)
            .orElse(null);
        if(likesEntity != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already clicked like");
        }
    }

    @Transactional(readOnly = true)
    public LikesDto findUseYnFalseOrCreateEmptyEntity(Long userId, Long postId, Boolean useYn){
        return likesMapper.toDto(likesRepository.findByUserIdAndPostId(userId, postId, useYn)
            .orElse(LikesEntity.builder().build()));
    }

    @Transactional(readOnly = true)
    public LikesDto findByUserIdAndPostId(Long userId, Long postId, Boolean useYn){
        return likesMapper.toDto(likesRepository.findByUserIdAndPostId(userId, postId, useYn)
            .orElseThrow(() -> new EntityNotFoundException("Likes not found with userId : " + userId + ", postId : " + postId)));
    }

    @Transactional(readOnly = true)
    public Long countByPostId(Long postId){
        return likesRepository.countByPostId(postId);
    }

    @Transactional
    public LikesDto delete(LikesDto likesDto){
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        likesEntity.markDeleted();
        return likesMapper.toDto(likesRepository.save(likesEntity));
    }

    @Transactional
    public LikesDto reuse(LikesDto likesDto){
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        likesEntity.markReuse();
        return likesMapper.toDto(likesRepository.save(likesEntity));
    }
}
