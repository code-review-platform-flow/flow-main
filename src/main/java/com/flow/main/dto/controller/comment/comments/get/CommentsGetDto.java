package com.flow.main.dto.controller.comment.comments.get;

import com.flow.main.dto.controller.comment.replies.get.RepliesGetDto;
import java.util.List;
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
public class CommentsGetDto {

    private Long commentId;
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;
    private String commentContent;
    private List<RepliesGetDto> replies;

}
