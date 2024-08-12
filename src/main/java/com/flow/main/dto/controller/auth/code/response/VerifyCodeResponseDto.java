package com.flow.main.dto.controller.auth.code.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyCodeResponseDto {

    private Map<String, Object> apiResponse;
}
