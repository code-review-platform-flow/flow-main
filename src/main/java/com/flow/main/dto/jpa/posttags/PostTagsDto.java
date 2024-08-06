package com.flow.main.dto.jpa.posttags;

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
public class PostTagsDto {

    private Long postTagId;
    private Long postId;
    private Long tagId;
    private int version;

}
