package com.flow.main.dto.controller.coffeeChats.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeChatsGetAllRequestDto {
	private String email;
}
