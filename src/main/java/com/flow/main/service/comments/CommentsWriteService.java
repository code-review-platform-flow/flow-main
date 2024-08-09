package com.flow.main.service.comments;

import com.flow.main.dto.controller.comment.comments.request.CommentsWriteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.PostService;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsWriteService {

    private final PostsService postsService;
    private final CommentsService commentsService;

    public CommentsDto writeComments(Long postId, CommentsWriteRequestDto commentsWriteRequestDto){

        PostsDto postsDto = postsService.findByPostId(postId);

        CommentsDto commentsDto = CommentsDto.builder()
            .postId(postsDto.getPostId())
            .userId(postsDto.getUserId())
            .content(commentsWriteRequestDto.getCommentContent())
            .build();

        return commentsService.save(commentsDto);
    }

}
