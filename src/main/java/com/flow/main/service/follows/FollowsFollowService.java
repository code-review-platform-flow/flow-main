package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.follow.request.FollowRequestDto;
import com.flow.main.dto.controller.follow.follow.response.FollowResponseDto;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.event.alarm.AlarmEvent;
import com.flow.main.event.alarm.AlarmEventPublisher;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowsFollowService {

    private final UsersService usersService;
    private final FollowsService followsService;
    private final UserInfoService userInfoService;
    private final AlarmEventPublisher alarmEventPublisher;

    public FollowResponseDto follow(FollowRequestDto followRequestDto){

        UsersDto follower = usersService.findByEmail(followRequestDto.getFollowerEmail());
        UsersDto followee = usersService.findByEmail(followRequestDto.getFolloweeEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(follower.getUserId());

        followsService.checkAlreadyFollowed(follower.getUserId(), followee.getUserId());

        FollowsDto followsDto = followsService.findOrEmptyByFollowerIdAndFolloweeIdUseYnFalse(follower.getUserId(), followee.getUserId());
        followsDto.setFollowerId(follower.getUserId());
        followsDto.setFolloweeId(followee.getUserId());

        FollowsDto savedFollowsDto = !followsDto.isUseYn() ? followsService.reuse(followsDto) : followsService.save(followsDto);

        // 알람 이벤트 발행
        alarmEventPublisher.alarm(AlarmEvent.builder()
            .userId(followee.getUserId()) // 팔로위
            .alarmType("FOLLOW")
            .message(userInfoDto.getUserName() + "님이 팔로우 했습니다")
            .isRead(false)
            .referenceId(followsDto.getFollowerId())
            .referenceTable("follows")
            .build());

        return FollowResponseDto.builder()
            .followId(savedFollowsDto.getFollowId())
            .build();
    }

}
