package com.flow.main.service.likes;

import com.flow.main.dto.controller.like.request.LikeClickRequestDto;
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
public class LikesClickService {

    private final LikesService likesService;
    private final UsersService usersService;
    private final PostsService postsService;

    public LikesDto clickLike(Long postId, LikeClickRequestDto likeClickRequestDto){

        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(likeClickRequestDto.getEmail());
        likesService.checkUserAlreadyClickLike(usersDto.getUserId(), postsDto.getPostId(), true);

        LikesDto likesDto = likesService.findUseYnFalseOrCreateEmptyEntity(usersDto.getUserId(), postsDto.getPostId(), false);

        if(!likesDto.isUseYn()) { // reClick
            return likesService.reuse(likesDto);
        }
        likesDto.setUserId(usersDto.getUserId());
        likesDto.setPostId(postsDto.getPostId());
        return likesService.save(likesDto);
    }

}
