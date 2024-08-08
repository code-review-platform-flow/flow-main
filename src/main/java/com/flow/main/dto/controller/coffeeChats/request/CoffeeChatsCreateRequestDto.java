package com.flow.main.dto.controller.coffeeChats.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeChatsCreateRequestDto {
	private String sender;
	private String receiver;
	private String contents;
}
