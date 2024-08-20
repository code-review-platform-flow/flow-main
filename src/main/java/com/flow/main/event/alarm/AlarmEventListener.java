package com.flow.main.event.alarm;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.service.alarm.AlarmCreateService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlarmEventListener {

	private final AlarmCreateService alarmCreateService;

	@Async
	@EventListener
	public void onAlarmEventHandler(AlarmEvent alarmEvent) {
		AlarmDto alarmDto = AlarmDto.builder()
			.userId(alarmEvent.getUserId())
			.alarmType(alarmEvent.getAlarmType())
			.message(alarmEvent.getMessage())
			.isRead(alarmEvent.getIsRead())
			.build();

		alarmCreateService.asyncCreate(alarmDto);
	}

}
