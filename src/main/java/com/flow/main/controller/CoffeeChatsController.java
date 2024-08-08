package com.flow.main.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flow.main.dto.controller.coffeeChats.request.CoffeeChatsCreateRequestDto;
import com.flow.main.dto.controller.coffeeChats.request.CoffeeChatsGetAllRequestDto;
import com.flow.main.dto.controller.coffeeChats.response.CoffeeChatsCreateResponseDto;
import com.flow.main.dto.controller.coffeeChats.response.CoffeeChatsGetAllResponseDto;
import com.flow.main.service.coffeeChats.CoffeeChatsCreateService;
import com.flow.main.service.coffeeChats.CoffeeChatsGetAllService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coffee")
@RequiredArgsConstructor
public class CoffeeChatsController {

	private final CoffeeChatsCreateService coffeeChatsCreateService;
	private final CoffeeChatsGetAllService coffeeChatsGetAllService;

	@PostMapping
	public ResponseEntity<CoffeeChatsCreateResponseDto> create(@RequestBody final CoffeeChatsCreateRequestDto requestDto) {
		return ResponseEntity.ok().body(coffeeChatsCreateService.create(requestDto));
	}

	@GetMapping
	public ResponseEntity<CoffeeChatsGetAllResponseDto> getAll(@RequestBody final CoffeeChatsGetAllRequestDto requestDto) {
		return ResponseEntity.ok().body(coffeeChatsGetAllService.getAll(requestDto));
	}

}
