package com.flow.main.controller;

import com.flow.main.dto.controller.comments.comments.request.CommentsWriteRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentsController {

    @PostMapping("/{postId}")
    public void writeComment(@PathVariable("postId") Long postId,
        @RequestBody final CommentsWriteRequestDto commentsWriteRequestDto){

    }

}
