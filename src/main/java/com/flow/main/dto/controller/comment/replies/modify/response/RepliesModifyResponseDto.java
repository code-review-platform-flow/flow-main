package com.flow.main.dto.controller.comment.replies.modify.response;

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
public class RepliesModifyResponseDto {

    public Long commentId;
    public Long replyId;

}
