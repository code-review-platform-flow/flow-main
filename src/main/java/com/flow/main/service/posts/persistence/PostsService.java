package com.flow.main.service.posts.persistence;

import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.PostsEntity;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.repository.PostsRepository;
import com.flow.main.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final PostsMapper postsMapper;

    @Transactional
    public PostsDto save(PostsDto postsDto){
        PostsEntity postsEntity = postsMapper.toEntity(postsDto);
        return postsMapper.toDto(postsRepository.save(postsEntity));
    }

    @Transactional(readOnly = true)
    public PostsDto findByPostId(Long postId){
        PostsEntity postsEntity = postsRepository.findByPostId(postId)
            .orElseThrow(() -> new EntityNotFoundException("Posts not found with postId : " + postId));
        return postsMapper.toDto(postsEntity);
    }

    @Transactional(readOnly = true)
    public PostsDto findByPostId(Long postId, Long userId){
        PostsEntity postsEntity = postsRepository.findByPostId(postId, userId)
            .orElseThrow(() -> new EntityNotFoundException("Posts not found with postId : " + postId + " , userId : " + userId));
        return postsMapper.toDto(postsEntity);
    }

    @Transactional
    public PostsDto delete(Long postId, Long userId){
        PostsEntity postsEntity = postsRepository.findByPostId(postId, userId)
            .orElseThrow(() -> new EntityNotFoundException("Posts not found with postId : " + postId + " , userId : " + userId));
        postsEntity.markDeleted();
        return postsMapper.toDto(postsRepository.save(postsEntity));
    }
}
