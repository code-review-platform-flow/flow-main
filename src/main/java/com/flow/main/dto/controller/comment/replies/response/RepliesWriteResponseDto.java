package com.flow.main.dto.controller.comment.replies.response;

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
public class RepliesWriteResponseDto {

    private Long commentId;
    private Long replyId;

}
