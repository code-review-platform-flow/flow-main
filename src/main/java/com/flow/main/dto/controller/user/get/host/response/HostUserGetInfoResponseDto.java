package com.flow.main.dto.controller.user.get.host.response;

import com.flow.main.dto.controller.user.get.host.CareerId;
import com.flow.main.dto.controller.user.get.host.EducationId;
import com.flow.main.dto.controller.user.get.host.PostId;
import java.util.List;
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
public class HostUserGetInfoResponseDto {

    private boolean own;
    private boolean followHost;
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;
    private String oneLiner;
    private Long followerCount;
    private List<EducationId> educationIdList;
    private List<CareerId> careerIdList;
    private List<PostId> postIdList;

}
