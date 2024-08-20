package com.flow.main.event.alarm;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlarmEventPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	public void alarm(AlarmEvent alarmEvent) {
		applicationEventPublisher.publishEvent(alarmEvent);
	}

}
