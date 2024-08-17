package com.flow.main.dto.jpa.posts;

import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.PostTagsEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PostsDto {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Long categoryId;
    private LocalDateTime createDate;
    private int version;
}
