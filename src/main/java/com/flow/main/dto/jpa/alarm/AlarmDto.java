package com.flow.main.dto.jpa.alarm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmDto {
	private Long alarmId;
	private Long userId;
	private String alarmType;
	private String message;
	private Boolean isRead;
	private Long referenceId;
	private String referenceTable;
}
