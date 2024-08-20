package com.flow.main.service.alarm;

import org.springframework.stereotype.Service;

import com.flow.main.dto.controller.alarm.response.AlarmClickResponseDto;
import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.alarm.persistence.AlarmService;
import com.flow.main.service.users.persistence.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmClickService {

	private final UsersService usersService;
	private final AlarmService alarmService;

	public AlarmClickResponseDto toggleIsRead(Long alarmId, String email) {
		UsersDto usersDto = usersService.findByEmail(email);
		AlarmDto alarmDto = alarmService.getAlarmByAlarmId(alarmId);

		AlarmDto toggleAlarm = AlarmDto.builder()
			.alarmId(alarmDto.getAlarmId())
			.userId(alarmDto.getUserId())
			.alarmType(alarmDto.getAlarmType())
			.message(alarmDto.getMessage())
			.isRead(!alarmDto.getIsRead())
			.referenceId(alarmDto.getReferenceId())
			.referenceTable(alarmDto.getReferenceTable())
			.build();

		AlarmDto savedAlarmDto = alarmService.save(toggleAlarm);

		return AlarmClickResponseDto.builder().alarmId(alarmId).isRead(savedAlarmDto.getIsRead()).build();
	}

}
