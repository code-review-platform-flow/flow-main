package com.flow.main.dto.controller.comment.replies.modify.request;

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
public class RepliesModifyRequestDto {

    private String email;
    private String replyContent;

}
