package com.flow.main.dto.controller.user.get.summary.response;

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
public class UserSummaryResponseDto {
    private String email;
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;

}
