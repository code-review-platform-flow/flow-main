package com.flow.main.dto.controller.follow.get.followee;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FolloweeGetDto {
    private Long followeeId;
    private String followeeEmail;
}
