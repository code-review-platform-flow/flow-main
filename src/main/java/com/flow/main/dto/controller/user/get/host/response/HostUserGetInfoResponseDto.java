package com.flow.main.dto.controller.user.get.host.response;

import com.flow.main.dto.controller.user.get.host.Career;
import com.flow.main.dto.controller.user.get.host.Education;
import com.flow.main.dto.controller.user.get.host.Post;
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
    private String profileUrl;
    private String userName;
    private String majorName;
    private String studentNumber;
    private String oneLiner;
    private Long followerCount;
    private List<Education> educationList;
    private List<Career> careerList;
    private List<Post> postList;

}
