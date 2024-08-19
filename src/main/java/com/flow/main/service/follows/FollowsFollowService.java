package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.follow.request.FollowRequestDto;
import com.flow.main.dto.controller.follow.follow.response.FollowResponseDto;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowsFollowService {

    private final UsersService usersService;
    private final FollowsService followsService;

    public FollowResponseDto follow(FollowRequestDto followRequestDto){

        UsersDto follower = usersService.findByEmail(followRequestDto.getFollowerEmail());
        UsersDto followee = usersService.findByEmail(followRequestDto.getFolloweeEmail());

        followsService.checkAlreadyFollowed(follower.getUserId(), followee.getUserId());

        FollowsDto followsDto = followsService.findOrEmptyByFollowerIdAndFolloweeIdUseYnFalse(follower.getUserId(), followee.getUserId());
        followsDto.setFollowerId(follower.getUserId());
        followsDto.setFolloweeId(followee.getUserId());

        FollowsDto savedFollowsDto = !followsDto.isUseYn() ? followsService.reuse(followsDto) : followsService.save(followsDto);

        return FollowResponseDto.builder()
            .followId(savedFollowsDto.getFollowId())
            .build();
    }

}
