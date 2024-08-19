package com.flow.main.dto.jpa.follows;

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
public class FollowsDto {

    private Long followId;
    private Long followerId;
    private Long followeeId;
    private boolean useYn;
    private int version;

}
