package com.flow.main.controller;

import com.flow.main.dto.controller.like.click.request.LikeClickRequestDto;
import com.flow.main.dto.controller.like.click.response.LikeCLickResponseDto;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.service.likes.LikesClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikesClickService likesClickService;

    @PostMapping("/{postId}")
    public ResponseEntity<LikeCLickResponseDto> clickLike(@PathVariable("postId") Long postId, @RequestBody final LikeClickRequestDto likeClickRequestDto){
        LikesDto likesDto = likesClickService.clickLike(postId, likeClickRequestDto);
        return ResponseEntity.ok(LikeCLickResponseDto.builder()
            .likeId(likesDto.getLikeId()).build());
    }



}
