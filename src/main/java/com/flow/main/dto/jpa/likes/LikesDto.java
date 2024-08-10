package com.flow.main.dto.jpa.likes;

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
public class LikesDto {

    private Long likeId;
    private Long userId;
    private Long postId;
    private boolean useYn;
    private int version;
}
