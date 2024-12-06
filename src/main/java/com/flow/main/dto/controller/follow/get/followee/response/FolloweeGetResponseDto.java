package com.flow.main.dto.controller.follow.get.followee.response;

import com.flow.main.dto.controller.follow.get.followee.FolloweeIdDto;
import com.flow.main.dto.controller.follow.get.follower.FollowerIdDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FolloweeGetResponseDto {
    private String email;
    private Long followerId;
    private List<FolloweeIdDto> followeeIdList;
}
