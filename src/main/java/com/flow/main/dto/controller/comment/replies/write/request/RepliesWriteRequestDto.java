package com.flow.main.dto.controller.comment.replies.write.request;

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
public class RepliesWriteRequestDto {

    private String email;
    private String replyContent;

}
