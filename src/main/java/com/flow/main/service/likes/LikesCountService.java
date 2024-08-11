package com.flow.main.service.likes;

import com.flow.main.dto.controller.like.response.LikeCountResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.likes.persistence.LikesService;
import com.flow.main.service.posts.persistence.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesCountService {

    private final LikesService likesService;
    private final PostsService postsService;

    public LikeCountResponseDto getLikeCount(Long postId){
        PostsDto postsDto = postsService.findByPostId(postId);
        Long likeCount = likesService.countByPostId(postsDto.getPostId());

        return LikeCountResponseDto.builder()
            .postId(postsDto.getPostId())
            .likeCount(likeCount)
            .build();
    }
}
