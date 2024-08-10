package com.flow.main.controller;

import com.flow.main.dto.controller.comment.comments.request.CommentsDeleteRequestDto;
import com.flow.main.dto.controller.comment.comments.request.CommentsModifyRequestDto;
import com.flow.main.dto.controller.comment.comments.request.CommentsWriteRequestDto;
import com.flow.main.dto.controller.comment.comments.response.CommentsDeleteResponseDto;
import com.flow.main.dto.controller.comment.comments.response.CommentsModifyResponseDto;
import com.flow.main.dto.controller.comment.comments.response.CommentsWriteResponseDto;
import com.flow.main.dto.controller.comment.replies.request.RepliesDeleteRequestDto;
import com.flow.main.dto.controller.comment.replies.request.RepliesModifyRequestDto;
import com.flow.main.dto.controller.comment.replies.request.RepliesWriteRequestDto;
import com.flow.main.dto.controller.comment.replies.response.RepliesDeleteResponseDto;
import com.flow.main.dto.controller.comment.replies.response.RepliesModifyResponseDto;
import com.flow.main.dto.controller.comment.replies.response.RepliesWriteResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.service.comments.CommentsDeleteService;
import com.flow.main.service.comments.CommentsModifyService;
import com.flow.main.service.comments.CommentsWriteService;
import com.flow.main.service.replies.RepliesDeleteService;
import com.flow.main.service.replies.RepliesModifyService;
import com.flow.main.service.replies.RepliesWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    private final CommentsModifyService commentsModifyService;
    private final CommentsDeleteService commentsDeleteService;
    private final RepliesWriteService repliesWriteService;
    private final RepliesModifyService repliesModifyService;
    private final RepliesDeleteService repliesDeleteService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentsWriteResponseDto> writeComments(@PathVariable("postId") Long postId, @RequestBody final CommentsWriteRequestDto commentsWriteRequestDto){
        CommentsDto commentsDto = commentsWriteService.writeComments(postId, commentsWriteRequestDto);
        return ResponseEntity.ok(CommentsWriteResponseDto.builder().commentId(commentsDto.getCommentId()).build());
    }

    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentsModifyResponseDto> modifyComments(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final CommentsModifyRequestDto commentsModifyRequestDto){
        CommentsDto commentsDto = commentsModifyService.modifyComments(postId, commentId,
            commentsModifyRequestDto);
        return ResponseEntity.ok(CommentsModifyResponseDto.builder()
            .commentId(commentsDto.getCommentId()).build());
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentsDeleteResponseDto> deleteComments(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final CommentsDeleteRequestDto commentsDeleteRequestDto){
        CommentsDto commentsDto = commentsDeleteService.deleteComments(postId, commentId, commentsDeleteRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<RepliesWriteResponseDto> writeReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final RepliesWriteRequestDto repliesWriteRequestDto){
        RepliesDto repliesDto = repliesWriteService.writeReplies(postId, commentId, repliesWriteRequestDto);
        return ResponseEntity.ok(RepliesWriteResponseDto.builder()
            .commentId(repliesDto.getCommentId())
            .replyId(repliesDto.getReplyId())
            .build());
    }

    @PatchMapping("/{postId}/{commentId}/reply/{replyId}")
    public ResponseEntity<RepliesModifyResponseDto> modifyReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @PathVariable("replyId") Long replyId, @RequestBody final RepliesModifyRequestDto repliesModifyRequestDto){
        RepliesDto repliesDto = repliesModifyService.updateReplies(postId, commentId, replyId, repliesModifyRequestDto);
        return ResponseEntity.ok(RepliesModifyResponseDto.builder()
            .commentId(repliesDto.getCommentId())
            .replyId(repliesDto.getReplyId())
            .build());
    }

    @DeleteMapping("/{postId}/{commentId}/reply/{replyId}")
    public ResponseEntity<RepliesDeleteResponseDto> deleteReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @PathVariable("replyId") Long replyId, @RequestBody final RepliesDeleteRequestDto repliesDeleteRequestDto){
        RepliesDto repliesDto = repliesDeleteService.deleteReplies(postId, commentId, replyId, repliesDeleteRequestDto);
        return ResponseEntity.ok().build();
    }

}
