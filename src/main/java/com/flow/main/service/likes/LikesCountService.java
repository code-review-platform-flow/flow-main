package com.flow.main.service.likes;

import com.flow.main.dto.controller.like.response.LikeCountResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.likes.persistence.LikesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesCountService {

    private final UsersService usersService;
    private final LikesService likesService;
    private final PostsService postsService;

    public LikeCountResponseDto getLikeCount(Long postId, String email){
        UsersDto requestUser = email != null ? usersService.findByEmail(email) : null;
        PostsDto postsDto = postsService.findByPostId(postId);
        Long likeCount = likesService.countByPostId(postsDto.getPostId());
        boolean clicked = requestUser != null && likesService.existsByPostIdAndUserId(postsDto.getPostId(), requestUser.getUserId());

        return LikeCountResponseDto.builder()
            .clicked(clicked)
            .postId(postsDto.getPostId())
            .likeCount(likeCount)
            .build();
    }
}
