package com.flow.main.dto.controller.alarm.response;

import java.util.List;

import com.flow.main.dto.jpa.alarm.AlarmDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmGetAllResponseDto {

	private List<AlarmDto> items;

}
