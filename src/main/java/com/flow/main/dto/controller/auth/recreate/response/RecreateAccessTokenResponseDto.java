package com.flow.main.dto.controller.auth.recreate.response;

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
public class RecreateAccessTokenResponseDto {

    private String accessToken;

}
