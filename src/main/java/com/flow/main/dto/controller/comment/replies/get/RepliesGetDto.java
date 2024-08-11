package com.flow.main.dto.controller.comment.replies.get;

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
public class RepliesGetDto {

    private Long replyId;
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;
    private String replyContent;

}
