package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.request.CommentsDeleteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsDeleteService {

    private final PostsService postsService;
    private final UsersService usersService;
    private final CommentsService commentsService;

    public CommentsDto deleteComments(Long postId, Long commentId, CommentsDeleteRequestDto commentsDeleteRequestDto){

        /*
        * 1. 포스트가 유효 해야함
        * 2. 내가 작성한 comment 여야 함
        * */

        postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(commentsDeleteRequestDto.getEmail());

        CommentsDto commentsDto = commentsService.findByCommentId(commentId, usersDto.getUserId());
        return commentsService.delete(commentsDto);
    }

}
