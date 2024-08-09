package com.flow.main.controller;

import com.flow.main.dto.controller.comment.comments.request.CommentsWriteRequestDto;
import com.flow.main.dto.controller.comment.comments.response.CommentsWriteResponseDto;
import com.flow.main.dto.controller.comment.replies.request.RepliesWriteRequestDto;
import com.flow.main.dto.controller.comment.replies.response.RepliesWriteResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.service.comments.CommentsWriteService;
import com.flow.main.service.replies.RepliesWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentsWriteService commentsWriteService;
    private final RepliesWriteService repliesWriteService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentsWriteResponseDto> writeComments(@PathVariable("postId") Long postId, @RequestBody final CommentsWriteRequestDto commentsWriteRequestDto){
        CommentsDto commentsDto = commentsWriteService.writeComments(postId, commentsWriteRequestDto);
        return ResponseEntity.ok(CommentsWriteResponseDto.builder().commentId(commentsDto.getCommentId()).build());
    }

    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<RepliesWriteResponseDto> writeReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final RepliesWriteRequestDto repliesWriteRequestDto){
        RepliesDto repliesDto = repliesWriteService.writeReplies(commentId, repliesWriteRequestDto);
        return ResponseEntity.ok(RepliesWriteResponseDto.builder()
            .commentId(repliesDto.getCommentId())
            .replyId(repliesDto.getReplyId())
            .build());
    }
}
