package com.flow.main.service.coffeeChats;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flow.main.dto.controller.coffeeChats.request.CoffeeChatsGetAllRequestDto;
import com.flow.main.dto.controller.coffeeChats.response.CoffeeChatsGetAllResponseDto;
import com.flow.main.dto.jpa.coffeeChats.CoffeeChatsDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.coffeeChats.persistence.CoffeeChatsService;
import com.flow.main.service.users.persistence.UsersService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeChatsGetAllService {

	private final CoffeeChatsService coffeeChatsService;
	private final UsersService usersService;

	public CoffeeChatsGetAllResponseDto getAllWithPageable(String email, Pageable pageable) {
		UsersDto usersDto = usersService.findByEmail(email);
		List<CoffeeChatsDto> coffeeChatsDtoList = coffeeChatsService.getAllByInitiatorUserIdWithPageable(usersDto.getUserId(), pageable);
		return CoffeeChatsGetAllResponseDto.builder().coffeeChat(coffeeChatsDtoList).pageable(pageable).build();
	}

}
