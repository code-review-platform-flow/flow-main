package com.flow.main.service.userinfo;

import com.flow.main.dto.controller.user.get.host.CareerId;
import com.flow.main.dto.controller.user.get.host.EducationId;
import com.flow.main.dto.controller.user.get.host.PostId;
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
public class UserInfoGetHostService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final MajorService majorService;
    private final FollowsService followsService;
    private final CareerService careerService;
    private final EducationService educationService;
    private final PostsService postsService;

    public HostUserGetInfoResponseDto getHostUserInfo(String hostEmail, String visitorEmail){

        UsersDto hostUser = usersService.findByEmail(hostEmail);
        UsersDto visitorUser = visitorEmail != null ? usersService.findByEmail(visitorEmail) : null;
        boolean own = visitorUser != null && Objects.equals(hostUser.getUserId(), visitorUser.getUserId());
        boolean followHost = visitorUser != null && followsService.existsByFolloweeIdAndFollowerId(hostUser.getUserId(), visitorUser.getUserId());

        UserInfoDto hostUserInfoDto = userInfoService.findByUserId(hostUser.getUserId());
        MajorDto hostMajorDto = majorService.findByMajorId(hostUserInfoDto.getMajorId());
        Long followerCount = followsService.countByFolloweeId(hostUser.getUserId());
        List<EducationDto> hostAllEducation = educationService.findAllByUserInfoId(hostUserInfoDto.getUserInfoId());
        List<CareerDto> hostAllCareer = careerService.findAllByUserInfoId(hostUserInfoDto.getUserInfoId());
        List<PostsDto> hostAllPosts = postsService.findAllByUserId(hostUser.getUserId());

        List<EducationId> educationList = hostAllEducation.stream()
            .map(educationDto -> EducationId.builder().educationId(educationDto.getEducationId()).build())
            .collect(Collectors.toList());
        List<CareerId> careerIdList = hostAllCareer.stream()
            .map(careerDto -> CareerId.builder().careerId(careerDto.getCareerId()).build())
            .collect(Collectors.toList());
        List<PostId> postIdList = hostAllPosts.stream()
            .map(postsDto -> PostId.builder().postId(postsDto.getPostId()).build())
            .collect(Collectors.toList());

        return HostUserGetInfoResponseDto.builder()
            .own(own)
            .followHost(followHost)
            .profileUrl(hostUserInfoDto.getProfileUrl())
            .userName(hostUserInfoDto.getUserName())
            .majorName(hostMajorDto.getMajorName())
            .studentNumber(hostUserInfoDto.getStudentNumber())
            .oneLiner(hostUserInfoDto.getOneLiner())
            .followerCount(followerCount)
            .educationIdList(educationList)
            .careerIdList(careerIdList)
            .postIdList(postIdList)
            .build();
    }

}
