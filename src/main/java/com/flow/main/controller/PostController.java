package com.flow.main.controller;

import com.flow.main.dto.controller.post.delete.request.PostDeleteRequestDto;
import com.flow.main.dto.controller.post.save.request.PostSaveRequestDto;
import com.flow.main.dto.controller.post.save.response.PostSaveResponseDto;
import com.flow.main.service.PostService;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<PostSaveResponseDto> save(@RequestBody final PostSaveRequestDto postSaveRequestDto){
        return postService.save(postSaveRequestDto);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostSaveResponseDto> modify(@RequestBody final PostSaveRequestDto postSaveRequestDto, @PathVariable("postId") Long postId){
        return postService.modify(postSaveRequestDto, postId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable("postId") Long postId, @RequestBody final
        PostDeleteRequestDto postDeleteRequestDto){
        return postService.delete(postId, postDeleteRequestDto);
    }
}
