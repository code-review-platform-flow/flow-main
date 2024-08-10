package com.flow.main.controller;

import com.flow.main.dto.controller.post.delete.request.PostDeleteRequestDto;
import com.flow.main.dto.controller.post.delete.response.PostDeleteResponseDto;
import com.flow.main.dto.controller.post.modify.request.PostModifyRequestDto;
import com.flow.main.dto.controller.post.modify.response.PostModifyResponseDto;
import com.flow.main.dto.controller.post.write.request.PostWriteRequestDto;
import com.flow.main.dto.controller.post.write.response.PostWriteResponseDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.service.posts.PostsDeleteService;
import com.flow.main.service.posts.PostsModifyService;
import com.flow.main.service.posts.PostsWriteService;
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

    private final PostsWriteService postsWriteService;
    private final PostsModifyService postsModifyService;
    private final PostsDeleteService postsDeleteService;

    @PostMapping("")
    public ResponseEntity<PostWriteResponseDto> write(@RequestBody final PostWriteRequestDto postWriteRequestDto){
        PostsDto postsDto = postsWriteService.write(postWriteRequestDto);
        return ResponseEntity.ok(PostWriteResponseDto.builder()
            .postId(postsDto.getPostId())
            .build());
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostModifyResponseDto> modify(@PathVariable("postId") Long postId, @RequestBody final PostModifyRequestDto postModifyRequestDto){
        PostsDto postsDto = postsModifyService.modify(postId, postModifyRequestDto);
        return ResponseEntity.ok(PostModifyResponseDto.builder()
            .postId(postsDto.getPostId())
            .build());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> delete(@PathVariable("postId") Long postId, @RequestBody final
        PostDeleteRequestDto postDeleteRequestDto){
        PostsDto postsDto = postsDeleteService.delete(postId, postDeleteRequestDto);
        return ResponseEntity.ok().build();
    }
}
