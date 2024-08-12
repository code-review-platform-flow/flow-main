package com.flow.main.dto.controller.auth.email.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailRequestDto {

    private String email;
    private String universityName;

}
