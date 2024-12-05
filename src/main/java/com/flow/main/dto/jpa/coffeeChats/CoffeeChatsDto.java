package com.flow.main.dto.jpa.coffeeChats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeChatsDto {
	private Long coffeeId;
	private Long initiatorUserId;
	private String initiatorUserEmail;
	private Long recipientUserId;
	private String recipientUserEmail;
	private String contents;
	private LocalDateTime createDate;
}