package com.flow.main.service.coffeeChats;

import org.springframework.stereotype.Service;

import com.flow.main.dto.controller.coffeeChats.request.CoffeeChatsCreateRequestDto;
import com.flow.main.dto.controller.coffeeChats.response.CoffeeChatsCreateResponseDto;
import com.flow.main.dto.jpa.coffeeChats.CoffeeChatsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.coffeeChats.persistence.CoffeeChatsService;
import com.flow.main.service.users.persistence.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeChatsCreateService {

	private final CoffeeChatsService coffeeChatsService;
	private final UsersService usersService;

	public CoffeeChatsCreateResponseDto create(CoffeeChatsCreateRequestDto coffeeChatsCreateRequestDto) {
		UsersDto senderUserDto = usersService.findByEmail(coffeeChatsCreateRequestDto.getSender());
		UsersDto receiverUserDto = usersService.findByEmail(coffeeChatsCreateRequestDto.getReceiver());
		CoffeeChatsDto coffeeChatsDto = CoffeeChatsDto.builder()
			.initiatorUserId(senderUserDto.getUserId())
			.recipientUserId(receiverUserDto.getUserId())
			.contents(coffeeChatsCreateRequestDto.getContents())
			.build();

		CoffeeChatsDto savedCoffeeChatsDto = coffeeChatsService.save(coffeeChatsDto);

		return CoffeeChatsCreateResponseDto.builder().coffeeId(savedCoffeeChatsDto.getCoffeeId()).build();
	}

}
