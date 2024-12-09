package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.get.follower.FollowerGetDto;
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
        List<FollowerGetDto> followerGetDtos = getFollowerIds(followsDtos);

        return FollowerGetResponseDto.builder()
                .email(usersDto.getEmail())
                .followeeId(usersDto.getUserId())
                .followerList(followerGetDtos).build();
    }

    private List<FollowerGetDto> getFollowerIds(List<FollowsDto> followsDtos) {
        return followsDtos.stream()
                .map(followsDto -> FollowerGetDto.builder()
                        .followerId(followsDto.getFollowerId())
                        .followerEmail(getFollowerEmail(followsDto))
                        .build())
                .toList();
    }

    private String getFollowerEmail(FollowsDto followsDto) {
        UsersDto usersDto = usersService.findByUserId(followsDto.getFollowerId());
        return usersDto.getEmail();
    }
}
