package com.flow.main.dto.controller.user.withdrawal.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWithdrawalRequestDto {

    private String email;
    private String reason;

}
