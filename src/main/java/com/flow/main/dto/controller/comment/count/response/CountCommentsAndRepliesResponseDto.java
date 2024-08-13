package com.flow.main.dto.controller.comment.count.response;

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
public class CountCommentsAndRepliesResponseDto {

    private Long commentsAndRepliesCount;

}
