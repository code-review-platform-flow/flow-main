package com.flow.main.service.posts;

import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.posts.persistence.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsLatestService {

    private final PostsService postsService;

    public PostsDto getLatest(){
        return postsService.findLatestByCreateDate();
    }

}
