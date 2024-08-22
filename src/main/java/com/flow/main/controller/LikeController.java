package com.flow.main.controller;

import com.flow.main.dto.controller.like.request.LikeCancelRequestDto;
import com.flow.main.dto.controller.like.response.LikeCancelResponseDto;
import com.flow.main.dto.controller.like.request.LikeClickRequestDto;
import com.flow.main.dto.controller.like.response.LikeCLickResponseDto;
import com.flow.main.dto.controller.like.response.LikeCountResponseDto;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.service.likes.LikesCancelService;
import com.flow.main.service.likes.LikesClickService;
import com.flow.main.service.likes.LikesCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikesClickService likesClickService;
    private final LikesCancelService likesCancelService;
    private final LikesCountService likesCountService;

    @GetMapping("/{postId}")
    public ResponseEntity<LikeCountResponseDto> countLike(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(likesCountService.getLikeCount(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<LikeCLickResponseDto> clickLike(@PathVariable("postId") Long postId, @RequestBody final LikeClickRequestDto likeClickRequestDto){
        log.info("postId : {}", postId);
        log.info("email : {}", likeClickRequestDto.getEmail());

        return ResponseEntity.ok(likesClickService.clickLike(postId, likeClickRequestDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<LikeCancelResponseDto> cancelLike(@PathVariable("postId") Long postId, @RequestBody final
        LikeCancelRequestDto likeCancelRequestDto){
        log.info("postId : {}", postId);
        log.info("email : {}", likeCancelRequestDto.getEmail());

        return ResponseEntity.ok(likesCancelService.cancelLike(postId, likeCancelRequestDto));
    }

}
