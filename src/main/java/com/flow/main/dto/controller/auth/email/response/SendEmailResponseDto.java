package com.flow.main.dto.controller.auth.email.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailResponseDto {

    private Map<String, Object> apiResponse;

}
