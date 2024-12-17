package com.flow.main.service.coffeeChats;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

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
		List<CoffeeChatsDto> coffeeChatsDtoListInitiator = coffeeChatsService.getAllByInitiatorUserIdWithPageable(usersDto.getUserId(), pageable);
		List<CoffeeChatsDto> coffeeChatsDtoListRecipient = coffeeChatsService.getAllByRecipientUserIdWithPageable(usersDto.getUserId(), pageable);
		
		List<CoffeeChatsDto> combinedList = Stream.concat(
				coffeeChatsDtoListInitiator.stream(),
				coffeeChatsDtoListRecipient.stream()
		).collect(Collectors.toList());
		
		return CoffeeChatsGetAllResponseDto.builder()
				.coffeeChat(combinedList)
				.pageable(pageable)
				.build();
	}

}
