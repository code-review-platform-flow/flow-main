package com.flow.main.dto.controller.user.update.oneliner.request;

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
public class UserOneLinerUpdateRequestDto {

    private String email;
    private String oneLiner;

}
