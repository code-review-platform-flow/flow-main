package com.flow.main.dto.controller.follow.get.follower.response;

import com.flow.main.dto.controller.follow.get.follower.FollowerIdDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowerGetResponseDto {
    private String email;
    private Long followeeId;
    private List<FollowerIdDto> followerIdList;
}
