package com.flow.main.dto.controller.follow.get.follower;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowerGetDto {
    private Long followerId;
    private String followerEmail;
}
