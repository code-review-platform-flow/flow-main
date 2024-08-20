package com.flow.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flow.main.dto.controller.alarm.request.AlarmGetAllRequestDto;
import com.flow.main.dto.controller.alarm.response.AlarmGetAllResponseDto;
import com.flow.main.service.alarm.AlarmGetAllService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {

	private final AlarmGetAllService alarmGetAllService;

	@GetMapping
	public ResponseEntity<AlarmGetAllResponseDto> get(@RequestParam String email) {
		return ResponseEntity.ok().body(alarmGetAllService.getAllByEmail(email));
	}

}
