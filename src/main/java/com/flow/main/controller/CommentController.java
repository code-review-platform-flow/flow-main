package com.flow.main.controller;

import com.flow.main.dto.controller.comment.comments.delete.request.CommentsDeleteRequestDto;
import com.flow.main.dto.controller.comment.comments.get.response.CommentsGetAllResponseDto;
import com.flow.main.dto.controller.comment.comments.modify.request.CommentsModifyRequestDto;
import com.flow.main.dto.controller.comment.comments.write.request.CommentsWriteRequestDto;
import com.flow.main.dto.controller.comment.comments.delete.response.CommentsDeleteResponseDto;
import com.flow.main.dto.controller.comment.comments.modify.response.CommentsModifyResponseDto;
import com.flow.main.dto.controller.comment.comments.write.response.CommentsWriteResponseDto;
import com.flow.main.dto.controller.comment.count.response.CountCommentsAndRepliesResponseDto;
import com.flow.main.dto.controller.comment.replies.delete.request.RepliesDeleteRequestDto;
import com.flow.main.dto.controller.comment.replies.modify.request.RepliesModifyRequestDto;
import com.flow.main.dto.controller.comment.replies.write.request.RepliesWriteRequestDto;
import com.flow.main.dto.controller.comment.replies.delete.response.RepliesDeleteResponseDto;
import com.flow.main.dto.controller.comment.replies.modify.response.RepliesModifyResponseDto;
import com.flow.main.dto.controller.comment.replies.write.response.RepliesWriteResponseDto;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.service.comments.CommentsCountService;
import com.flow.main.service.comments.CommentsDeleteService;
import com.flow.main.service.comments.CommentsGetAllService;
import com.flow.main.service.comments.CommentsModifyService;
import com.flow.main.service.comments.CommentsWriteService;
import com.flow.main.service.replies.RepliesDeleteService;
import com.flow.main.service.replies.RepliesModifyService;
import com.flow.main.service.replies.RepliesWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentsGetAllService commentsGetAllService;
    private final CommentsCountService commentsCountService;
    private final CommentsWriteService commentsWriteService;
    private final CommentsModifyService commentsModifyService;
    private final CommentsDeleteService commentsDeleteService;
    private final RepliesWriteService repliesWriteService;
    private final RepliesModifyService repliesModifyService;
    private final RepliesDeleteService repliesDeleteService;

    @GetMapping("/{postId}")
    public ResponseEntity<CommentsGetAllResponseDto> getAllCommentsAndReplies(@PathVariable("postId") Long postId){
        log.info("postId : {}", postId);

        return ResponseEntity.ok(commentsGetAllService.getAllCommentsAndReplies(postId));
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<CountCommentsAndRepliesResponseDto> countCommentsAndReplies(@PathVariable("postId") Long postId){
        log.info("postId : {}", postId);

        return ResponseEntity.ok(commentsCountService.countCommentsAndReplies(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentsWriteResponseDto> writeComments(@PathVariable("postId") Long postId, @RequestBody final CommentsWriteRequestDto commentsWriteRequestDto){
        log.info("postId : {}", postId);
        log.info("email : {}", commentsWriteRequestDto.getEmail());
        log.info("commentContent : {}", commentsWriteRequestDto.getCommentContent());

        return ResponseEntity.ok(commentsWriteService.writeComments(postId, commentsWriteRequestDto));
    }

    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentsModifyResponseDto> modifyComments(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final CommentsModifyRequestDto commentsModifyRequestDto){
        log.info("postId : {}", postId);
        log.info("commentId : {}", commentId);
        log.info("email : {}", commentsModifyRequestDto.getEmail());
        log.info("commentContent : {}", commentsModifyRequestDto.getCommentContent());

        return ResponseEntity.ok(commentsModifyService.modifyComments(postId, commentId, commentsModifyRequestDto));
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentsDeleteResponseDto> deleteComments(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final CommentsDeleteRequestDto commentsDeleteRequestDto){
        log.info("postId : {}", postId);
        log.info("commentId : {}", commentId);
        log.info("email : {}", commentsDeleteRequestDto.getEmail());

        return ResponseEntity.ok(commentsDeleteService.deleteComments(postId, commentId, commentsDeleteRequestDto));
    }

    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<RepliesWriteResponseDto> writeReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody final RepliesWriteRequestDto repliesWriteRequestDto){
        log.info("postId : {}", postId);
        log.info("commentId : {}", commentId);
        log.info("email : {}", repliesWriteRequestDto.getEmail());
        log.info("replyContent : {}", repliesWriteRequestDto.getReplyContent());

        return ResponseEntity.ok(repliesWriteService.writeReplies(postId, commentId, repliesWriteRequestDto));
    }

    @PatchMapping("/{postId}/{commentId}/reply/{replyId}")
    public ResponseEntity<RepliesModifyResponseDto> modifyReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @PathVariable("replyId") Long replyId, @RequestBody final RepliesModifyRequestDto repliesModifyRequestDto){
        log.info("postId : {}", postId);
        log.info("commentId : {}", commentId);
        log.info("replyId : {}", replyId);
        log.info("email : {}", repliesModifyRequestDto.getEmail());
        log.info("replyContent : {}", repliesModifyRequestDto.getReplyContent());

        return ResponseEntity.ok(repliesModifyService.updateReplies(postId, commentId, replyId, repliesModifyRequestDto));
    }

    @DeleteMapping("/{postId}/{commentId}/reply/{replyId}")
    public ResponseEntity<RepliesDeleteResponseDto> deleteReplies(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @PathVariable("replyId") Long replyId, @RequestBody final RepliesDeleteRequestDto repliesDeleteRequestDto){
        log.info("postId : {}", postId);
        log.info("commentId : {}", commentId);
        log.info("replyId : {}", replyId);
        log.info("email : {}", repliesDeleteRequestDto.getEmail());

        return ResponseEntity.ok(repliesDeleteService.deleteReplies(postId, commentId, replyId, repliesDeleteRequestDto));
    }

}
