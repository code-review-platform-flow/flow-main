package com.flow.main.service.hof;

import com.flow.main.dto.controller.hof.get.UserId;
import com.flow.main.dto.controller.hof.get.response.HoFGetUsersResponseDto;
import com.flow.main.dto.jpa.hof.HallOfFameDto;
import com.flow.main.service.hof.persistence.HallOfFameService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoFGetUsersService {

    private final HallOfFameService hallOfFameService;

    public HoFGetUsersResponseDto getHoFUsers(Long count){

        List<HallOfFameDto> hofUsers = hallOfFameService.findAllByDateAwarded();
        List<UserId> userList = hofUsers.stream()
            .limit(count)
            .map(hallOfFameDto -> UserId.builder().userId(hallOfFameDto.getUserId()).build()
            ).toList();

        return HoFGetUsersResponseDto.builder()
            .userList(userList)
            .build();
    }

}
