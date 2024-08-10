package com.flow.main.dto.controller.post.write.request;

import com.flow.main.dto.controller.post.PostTagsNameDto;
import java.util.ArrayList;
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
public class PostWriteRequestDto {
    private String email;
    private String category;
    private ArrayList<PostTagsNameDto> tags;
    private String title;
    private String content;
}
