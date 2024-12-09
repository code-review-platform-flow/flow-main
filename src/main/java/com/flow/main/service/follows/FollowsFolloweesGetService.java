package com.flow.main.service.follows;

import com.flow.main.dto.controller.follow.get.followee.FolloweeGetDto;
import com.flow.main.dto.controller.follow.get.followee.response.FolloweeGetResponseDto;
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
        List<FolloweeGetDto> followeeGetDtos = getFolloweeIds(followsDtos);

        return FolloweeGetResponseDto.builder()
                .email(usersDto.getEmail())
                .followerId(usersDto.getUserId())
                .followeeList(followeeGetDtos).build();
    }

    private List<FolloweeGetDto> getFolloweeIds(List<FollowsDto> followsDtos) {
        return followsDtos.stream()
                .map(followsDto -> FolloweeGetDto.builder()
                        .followeeId(followsDto.getFolloweeId())
                        .followeeEmail(getFolloweeEmail(followsDto))
                        .build())
                .toList();
    }

    private String getFolloweeEmail(FollowsDto followsDto) {
        UsersDto usersDto = usersService.findByUserId(followsDto.getFolloweeId());
        return usersDto.getEmail();
    }
}
