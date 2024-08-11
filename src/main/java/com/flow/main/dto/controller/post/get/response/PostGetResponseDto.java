package com.flow.main.dto.controller.post.get.response;

import com.flow.main.dto.controller.post.TagsNameDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostGetResponseDto {
    private Long postId;
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;
    private String title;
    private String content;
    private String categoryName;
    private List<TagsNameDto> tagsNameDtos;
    private LocalDateTime createDate;
}
