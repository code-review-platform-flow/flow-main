package com.flow.main.event.alarm;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AlarmEvent {
	private Long userId;
	private String alarmType;
	private String message;
	private Boolean isRead;
}
