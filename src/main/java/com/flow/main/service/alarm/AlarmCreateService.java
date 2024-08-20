package com.flow.main.service.alarm;

import org.springframework.stereotype.Service;

import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.service.alarm.persistence.AlarmService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmCreateService {

	private final AlarmService alarmService;

	public Void asyncCreate(AlarmDto alarmDto) {
		alarmService.save(alarmDto);
		return null;
	}

}
