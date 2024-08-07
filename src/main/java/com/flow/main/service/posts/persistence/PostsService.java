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
    private final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    @Transactional
    public PostsDto save(PostsDto postsDto){
        PostsEntity postsEntity = postsMapper.toEntity(postsDto);
        return postsMapper.toDto(postsRepository.save(postsEntity));
    }

    public PostsDto findByPostId(Long postId){
        PostsEntity postsEntity = postsRepository.findByPostId(postId)
            .orElseThrow(() -> new EntityNotFoundException("Posts not found with postId : " + postId));
        LOGGER.info("postId : {}", postsEntity.getPostId());
        LOGGER.info("userId : {}", postsEntity.getUser().getUserId());
        LOGGER.info("title : {}", postsEntity.getTitle());
        LOGGER.info("content : {}", postsEntity.getContent());
        LOGGER.info("categoryId : {}", postsEntity.getCategory().getCategoryId());
        LOGGER.info("version : {}", postsEntity.getVersion());
        LOGGER.info("PostTags : {}", postsEntity.getPostTagsEntities().get(1).getTag().getTagId());
        return postsMapper.toDto(postsEntity);
    }
}
