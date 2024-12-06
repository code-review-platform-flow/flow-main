package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.get.followee.FolloweeIdDto;
import com.flow.main.dto.controller.follow.get.followee.response.FolloweeGetResponseDto;
import com.flow.main.dto.controller.follow.get.follower.FollowerIdDto;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.follows.persistence.FollowsService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowsFolloweesGetService {
    private final UsersService usersService;
    private final FollowsService followsService;

    public FolloweeGetResponseDto getFollowees(String email) {
        UsersDto usersDto = usersService.findByEmail(email);
        List<FollowsDto> followsDtos = followsService.findAllByFollowerId(usersDto.getUserId());
        List<FolloweeIdDto> followeeIdDtos = getFolloweeIds(followsDtos);

        return FolloweeGetResponseDto.builder()
                .email(usersDto.getEmail())
                .followerId(usersDto.getUserId())
                .followeeIdList(followeeIdDtos).build();
    }

    private List<FolloweeIdDto> getFolloweeIds(List<FollowsDto> followsDtos) {
        return followsDtos.stream()
                .map(followsDto -> FolloweeIdDto.builder()
                        .followeeId(followsDto.getFolloweeId())
                        .build())
                .toList();
    }
}
