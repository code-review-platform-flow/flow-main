package com.flow.main.dto.jpa.userinfo;

import com.flow.main.entity.UsersEntity;
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
public class UserInfoDto {
    private Long userInfoId;
    private Long userId;
    private Long schoolId;
    private Long majorId;
    private String studentNumber;
    private String role;
    private String userName;
    private String profileUrl;
    private String oneLiner;
    private int version;
}
