package com.flow.main.dto.controller.like.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeCountResponseDto {
    private boolean clicked;
    private Long postId;
    private Long likeCount;
}
