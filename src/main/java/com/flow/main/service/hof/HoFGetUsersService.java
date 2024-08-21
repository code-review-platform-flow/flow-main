package com.flow.main.service.hof;

import com.flow.main.dto.controller.hof.get.UserEmail;
import com.flow.main.dto.controller.hof.get.response.HoFGetUsersResponseDto;
import com.flow.main.dto.jpa.hof.HallOfFameDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.hof.persistence.HallOfFameService;
import com.flow.main.service.users.persistence.UsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoFGetUsersService {

    private final HallOfFameService hallOfFameService;
    private final UsersService usersService;

    public HoFGetUsersResponseDto getHoFUsers(Long count){

        List<HallOfFameDto> hofUsers = hallOfFameService.findAllByDateAwarded();
        List<UsersDto> usersDtos = hofUsers.stream().limit(count).map(hallOfFameDto -> usersService.findByUserId(
            hallOfFameDto.getUserId())).toList();
        List<UserEmail> userList = usersDtos.stream()
            .map(usersDto -> UserEmail.builder().email(usersDto.getEmail()).build()
            ).toList();

        return HoFGetUsersResponseDto.builder()
            .userList(userList)
            .build();
    }

}
