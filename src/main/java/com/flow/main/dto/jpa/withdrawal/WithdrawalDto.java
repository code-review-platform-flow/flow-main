package com.flow.main.dto.jpa.withdrawal;

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
public class WithdrawalDto {

    private Long withdrawalId;
    private Long userId;
    private String reason;
    private int version;

}
