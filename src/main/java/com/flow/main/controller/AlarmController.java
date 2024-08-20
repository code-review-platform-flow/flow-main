package com.flow.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flow.main.dto.controller.alarm.request.AlarmClickRequestDto;
import com.flow.main.dto.controller.alarm.response.AlarmClickResponseDto;
import com.flow.main.dto.controller.alarm.response.AlarmGetAllResponseDto;
import com.flow.main.service.alarm.AlarmClickService;
import com.flow.main.service.alarm.AlarmGetAllService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {

	private final AlarmGetAllService alarmGetAllService;
	private final AlarmClickService alarmClickService;

	@GetMapping
	public ResponseEntity<AlarmGetAllResponseDto> get(@RequestParam String email) {
		return ResponseEntity.ok().body(alarmGetAllService.getAllByEmail(email));
	}

	@PostMapping("/{alarmId}")
	public ResponseEntity<AlarmClickResponseDto> clickAlarm(@PathVariable("alarmId") Long alarmId ,@RequestBody final AlarmClickRequestDto alarmClickRequestDto) {
		return ResponseEntity.ok().body(alarmClickService.toggleIsRead(alarmId, alarmClickRequestDto.getEmail()));
	}

}
