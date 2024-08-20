package com.flow.main.service.alarm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flow.main.dto.controller.alarm.request.AlarmGetAllRequestDto;
import com.flow.main.dto.controller.alarm.response.AlarmGetAllResponseDto;
import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.alarm.persistence.AlarmService;
import com.flow.main.service.users.persistence.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmGetAllService {

	private final AlarmService alarmService;
	private final UsersService usersService;

	public AlarmGetAllResponseDto getAllByEmail(String email) {
		UsersDto usersDto = usersService.findByEmail(email);
		List<AlarmDto> alarmDtoList = alarmService.getAllByUserId(usersDto.getUserId());
		return AlarmGetAllResponseDto.builder().items(alarmDtoList).build();
	}

}
