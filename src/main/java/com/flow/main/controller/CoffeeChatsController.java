package com.flow.main.controller;

import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{email}")
	public ResponseEntity<CoffeeChatsGetAllResponseDto> getAll(@PathVariable("email") final String email, Pageable pageable) {
		return ResponseEntity.ok().body(coffeeChatsGetAllService.getAllWithPageable(email, pageable));
	}
}
