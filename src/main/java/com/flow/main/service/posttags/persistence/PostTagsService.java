package com.flow.main.service.posttags.persistence;

import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.mapper.PostTagsMapper;
import com.flow.main.repository.PostTagsRepository;
import com.flow.main.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostTagsService {

    private final PostTagsRepository postTagsRepository;
    private final PostTagsMapper postTagsMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    @Transactional
    public PostTagsDto save(PostTagsDto postTagsDto){
        PostTagsEntity postTagsEntity = postTagsMapper.toEntity(postTagsDto);
        return postTagsMapper.toDto(postTagsRepository.save(postTagsEntity));
    }

    public List<PostTagsDto> findListByPostId(Long postId){
        List<PostTagsEntity> postTagsEntities = postTagsRepository.findByPostId(postId).orElse(
            Collections.emptyList());
        LOGGER.info("postTagsEntities : {}", postTagsEntities);
        List<PostTagsDto> postTagsDtos = new ArrayList<>();
        for (PostTagsEntity p : postTagsEntities){
            postTagsDtos.add(postTagsMapper.toDto(p));
        }
        LOGGER.info("postTagsDtos : {}", postTagsDtos);
        return postTagsDtos;
    }

    @Transactional(readOnly = true)
    public PostTagsDto findByPostIdAndTagId(Long postId, Long tagId){
        return postTagsMapper.toDto(postTagsRepository.findByPostIdAndTagId(postId, tagId)
            .orElseThrow(() -> new EntityNotFoundException("PostTags not found with postId : " + postId + " and tagId : " + tagId)));
    }

    @Transactional
    public PostTagsDto reuseOrSavePostTags(PostTagsDto postTagsDto){
        PostTagsEntity postTagsEntity = postTagsRepository.findByPostIdAndTagIdUseYnFalse(
                postTagsDto.getPostId(), postTagsDto.getTagId()).orElse(null);
        if(postTagsEntity == null){
            return postTagsMapper.toDto(postTagsRepository.save(postTagsMapper.toEntity(postTagsDto)));
        }
        postTagsEntity.markReuse();
        return postTagsMapper.toDto(postTagsRepository.save(postTagsEntity));

    }

    @Transactional
    public PostTagsDto delete(PostTagsDto postTagsDto) {
        PostTagsEntity postTagsEntity = postTagsMapper.toEntity(postTagsDto);
        postTagsEntity.markDeleted();
        return postTagsMapper.toDto(postTagsRepository.save(postTagsEntity));
    }
}
