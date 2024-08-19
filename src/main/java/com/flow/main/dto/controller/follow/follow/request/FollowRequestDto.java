package com.flow.main.dto.controller.follow.follow.request;

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
public class FollowRequestDto {
    private String followerEmail;
    private String followeeEmail;
}
