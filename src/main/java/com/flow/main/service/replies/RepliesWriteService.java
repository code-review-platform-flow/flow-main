package com.flow.main.service.replies;

import com.flow.main.dto.controller.comment.replies.request.RepliesWriteRequestDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.service.comments.persistence.CommentsService;
import com.flow.main.service.replies.persistence.RepliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepliesWriteService {

    private final CommentsService commentsService;
    private final RepliesService repliesService;

    public RepliesDto writeReplies(Long commentId, RepliesWriteRequestDto repliesWriteRequestDto){

        CommentsDto commentsDto = commentsService.findByCommentId(commentId);

        RepliesDto repliesDto = RepliesDto.builder()
            .commentId(commentsDto.getCommentId())
            .userId(commentsDto.getUserId())
            .content(repliesWriteRequestDto.getReplyContent())
            .build();

        return repliesService.save(repliesDto);
    }

}
