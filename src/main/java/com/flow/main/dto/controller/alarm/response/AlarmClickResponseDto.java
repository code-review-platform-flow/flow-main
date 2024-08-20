package com.flow.main.dto.controller.alarm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmClickResponseDto {
	private Long alarmId;
	private Boolean isRead;
}
