package com.flow.main.service.posts.persistence;

import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.PostsEntity;
import com.flow.main.mapper.PostsMapper;
import com.flow.main.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
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

}
