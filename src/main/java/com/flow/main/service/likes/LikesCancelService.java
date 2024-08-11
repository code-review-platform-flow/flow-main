package com.flow.main.service.likes;

import com.flow.main.dto.controller.like.request.LikeCancelRequestDto;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.likes.persistence.LikesService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesCancelService {

    private final LikesService likesService;
    private final UsersService usersService;
    private final PostsService postsService;

    public LikesDto cancelLike(Long postId, LikeCancelRequestDto likeCancelRequestDto){
        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(likeCancelRequestDto.getEmail());
        LikesDto likesDto = likesService.findByUserIdAndPostId(usersDto.getUserId(), postsDto.getPostId(), true);
        return likesService.delete(likesDto);
    }
}
