package com.flow.main.dto.controller.follow.get.followee.response;

import com.flow.main.dto.controller.follow.get.followee.FolloweeGetDto;
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
    private List<FolloweeGetDto> followeeList;
}
