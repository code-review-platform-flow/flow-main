package com.flow.main.dto.controller.auth.code.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyCodeRequestDto {

    private String email;
    private String universityName;
    private Integer code;

}
