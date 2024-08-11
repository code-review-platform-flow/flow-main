package com.flow.main.service.replies;

import com.flow.main.dto.controller.comment.replies.write.request.RepliesWriteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.replies.persistence.RepliesService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesWriteService {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final UsersService usersService;
    private final RepliesService repliesService;

    public RepliesDto writeReplies(Long postId, Long commentId, RepliesWriteRequestDto repliesWriteRequestDto){

        postsService.findByPostId(postId); //check valid post
        CommentsDto commentsDto = commentsService.findByCommentId(commentId);
        UsersDto usersDto = usersService.findByEmail(repliesWriteRequestDto.getEmail());

        RepliesDto repliesDto = RepliesDto.builder()
            .commentId(commentsDto.getCommentId())
            .userId(usersDto.getUserId())
            .content(repliesWriteRequestDto.getReplyContent())
            .build();

        return repliesService.save(repliesDto);
    }

}
