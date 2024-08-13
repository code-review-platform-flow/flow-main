package com.flow.main.dto.controller.comment.comments.get.response;

import com.flow.main.dto.controller.comment.comments.get.CommentsGetDto;
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
public class CommentsGetAllResponseDto {

    private List<CommentsGetDto> comments;

}
