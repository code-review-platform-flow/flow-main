package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.cancel.request.FollowCancelRequestDto;
import com.flow.main.dto.controller.follow.cancel.response.FollowCancelResponseDto;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowsCancelService {

    private final UsersService usersService;
    private final FollowsService followsService;

    public FollowCancelResponseDto followCancel(FollowCancelRequestDto followCancelRequestDto){

        UsersDto follower = usersService.findByEmail(followCancelRequestDto.getFollowerEmail());
        UsersDto followee = usersService.findByEmail(followCancelRequestDto.getFolloweeEmail());

        FollowsDto followsDto = followsService.findOrEmptyByFollowerIdAndFolloweeIdUseYnTrue(follower.getUserId(), followee.getUserId());

        FollowsDto DeletedDto = followsService.delete(followsDto);

        return FollowCancelResponseDto.builder().build();
    }

}
