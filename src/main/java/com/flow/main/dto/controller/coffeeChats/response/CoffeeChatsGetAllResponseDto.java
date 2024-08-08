package com.flow.main.dto.controller.coffeeChats.response;

import java.util.List;

import com.flow.main.dto.jpa.coffeeChats.CoffeeChatsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeChatsGetAllResponseDto {
	private List<CoffeeChatsDto> coffeeChat;
}
