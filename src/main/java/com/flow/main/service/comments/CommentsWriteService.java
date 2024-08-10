package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.request.CommentsWriteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsWriteService {

    private final PostsService postsService;
    private final UsersService usersService;
    private final CommentsService commentsService;

    public CommentsDto writeComments(Long postId, CommentsWriteRequestDto commentsWriteRequestDto){

        PostsDto postsDto = postsService.findByPostId(postId);
        UsersDto usersDto = usersService.findByEmail(commentsWriteRequestDto.getEmail());

        CommentsDto commentsDto = CommentsDto.builder()
            .postId(postsDto.getPostId())
            .userId(usersDto.getUserId())
            .content(commentsWriteRequestDto.getCommentContent())
            .build();

        return commentsService.save(commentsDto);
    }

}
