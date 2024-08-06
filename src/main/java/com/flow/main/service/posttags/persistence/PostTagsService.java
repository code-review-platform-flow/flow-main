package com.flow.main.service.posttags.persistence;

import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.mapper.PostTagsMapper;
import com.flow.main.repository.PostTagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostTagsService {

    private final PostTagsRepository postTagsRepository;
    private final PostTagsMapper postTagsMapper;

    @Transactional
    public PostTagsDto save(PostTagsDto postTagsDto){
        PostTagsEntity postTagsEntity = postTagsMapper.toEntity(postTagsDto);
        return postTagsMapper.toDto(postTagsRepository.save(postTagsEntity));
    }

}
