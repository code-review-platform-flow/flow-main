package com.flow.main.service.posts;

import com.flow.main.dto.controller.post.delete.request.PostDeleteRequestDto;
import com.flow.main.dto.controller.post.delete.response.PostDeleteResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsDeleteService {

    private final UsersService usersService;
    private final PostsService postsService;

    public PostDeleteResponseDto delete(Long postId, PostDeleteRequestDto postDeleteRequestDto){
        UsersDto usersDto = usersService.findByEmail(postDeleteRequestDto.getEmail());
        postsService.delete(postId, usersDto.getUserId());

        return PostDeleteResponseDto.builder().build();
    }

}
