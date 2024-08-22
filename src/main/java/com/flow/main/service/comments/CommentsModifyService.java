package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.modify.request.CommentsModifyRequestDto;
import com.flow.main.dto.controller.comment.comments.modify.response.CommentsModifyResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsModifyService {

    private final PostsService postsService;
    private final UsersService usersService;
    private final CommentsService commentsService;

    public CommentsModifyResponseDto modifyComments(Long postId, Long commentId, CommentsModifyRequestDto commentsModifyRequestDto){

        postsService.findByPostId(postId); // check valid post
        UsersDto usersDto = usersService.findByEmail(commentsModifyRequestDto.getEmail());

        CommentsDto commentsDto = commentsService.findByCommentId(commentId, usersDto.getUserId());
        commentsDto.setContent(commentsModifyRequestDto.getCommentContent());

        CommentsDto savedCommentsDto = commentsService.save(commentsDto);

        return CommentsModifyResponseDto.builder()
            .commentId(savedCommentsDto.getCommentId())
            .build();
    }

}
