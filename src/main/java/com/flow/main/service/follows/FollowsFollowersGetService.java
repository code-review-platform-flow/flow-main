package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.get.follower.FollowerIdDto;
import com.flow.main.dto.controller.follow.get.follower.response.FollowerGetResponseDto;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowsFollowersGetService {
    private final UsersService usersService;
    private final FollowsService followsService;

    public FollowerGetResponseDto getFollowers(String email) {
        UsersDto usersDto = usersService.findByEmail(email);
        List<FollowsDto> followsDtos = followsService.findAllByFolloweeId(usersDto.getUserId());
        List<FollowerIdDto> followerIdDtos = getFollowerIds(followsDtos);

        return FollowerGetResponseDto.builder()
                .email(usersDto.getEmail())
                .followeeId(usersDto.getUserId())
                .followerIdList(followerIdDtos).build();
    }

    private List<FollowerIdDto> getFollowerIds(List<FollowsDto> followsDtos) {
        return followsDtos.stream()
                .map(followsDto -> FollowerIdDto.builder()
                        .followerId(followsDto.getFollowerId())
                        .build())
                .toList();
    }
}
