package com.flow.main.service.user;

import com.flow.main.dto.controller.user.get.host.Career;
import com.flow.main.dto.controller.user.get.host.Education;
import com.flow.main.dto.controller.user.get.host.Post;
import com.flow.main.dto.controller.user.get.host.request.HostUserGetInfoRequestDto;
import com.flow.main.dto.controller.user.get.host.response.HostUserGetInfoResponseDto;
import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.career.persistence.CareerService;
import com.flow.main.service.education.persistence.EducationService;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.major.persistence.MajorService;
import com.flow.main.service.posts.persistence.PostsService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HostUserGetInfoService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final MajorService majorService;
    private final FollowsService followsService;
    private final CareerService careerService;
    private final EducationService educationService;
    private final PostsService postsService;

    public HostUserGetInfoResponseDto getHostUserInfo(String hostEmail, HostUserGetInfoRequestDto hostUserGetInfoRequestDto){

        String visitorEmail = hostUserGetInfoRequestDto.getVisitorEmail();

        UsersDto hostUsersDto = usersService.findByEmail(hostEmail);
        UsersDto visitorUsersDto = visitorEmail != null ? usersService.findByEmail(visitorEmail) : null;
        boolean own = visitorUsersDto != null && Objects.equals(hostUsersDto.getUserId(), visitorUsersDto.getUserId());

        UserInfoDto hostUserInfoDto = userInfoService.findByUserId(hostUsersDto.getUserId());
        MajorDto hostMajorDto = majorService.findByMajorId(hostUserInfoDto.getMajorId());
        Long followerCount = followsService.countByFolloweeId(hostUsersDto.getUserId());
        List<EducationDto> hostAllEducation = educationService.findAllByUserInfoId(hostUserInfoDto.getUserInfoId());
        List<CareerDto> hostAllCareer = careerService.findAllByUserInfoId(hostUserInfoDto.getUserInfoId());
        List<PostsDto> hostAllPosts = postsService.findAllByUserId(hostUsersDto.getUserId());

        List<Education> educationList = hostAllEducation.stream()
            .map(educationDto -> Education.builder().educationId(educationDto.getEducationId()).build())
            .collect(Collectors.toList());
        List<Career> careerList = hostAllCareer.stream()
            .map(careerDto -> Career.builder().careerId(careerDto.getCareerId()).build())
            .collect(Collectors.toList());
        List<Post> postList = hostAllPosts.stream()
            .map(postsDto -> Post.builder().postId(postsDto.getPostId()).build())
            .collect(Collectors.toList());

        return HostUserGetInfoResponseDto.builder()
            .own(own)
            .profileUrl(hostUserInfoDto.getProfileUrl())
            .userName(hostUserInfoDto.getUserName())
            .majorName(hostMajorDto.getMajorName())
            .studentNumber(hostUserInfoDto.getStudentNumber())
            .oneLiner(hostUserInfoDto.getOneLiner())
            .followerCount(followerCount)
            .educationList(educationList)
            .careerList(careerList)
            .postList(postList)
            .build();
    }

}
