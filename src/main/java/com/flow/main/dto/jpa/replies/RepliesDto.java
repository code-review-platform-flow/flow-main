package com.flow.main.dto.jpa.replies;

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
public class RepliesDto {
    private Long replyId;
    private Long commentId;
    private Long userId;
    private String content;
    private int version;
}
