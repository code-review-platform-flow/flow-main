package com.flow.main.dto.controller.follow.cancel.request;

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
public class FollowCancelRequestDto {

    private String followerEmail;
    private String followeeEmail;
}
