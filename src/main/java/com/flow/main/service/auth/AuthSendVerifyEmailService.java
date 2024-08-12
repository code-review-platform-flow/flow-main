package com.flow.main.service.auth;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.flow.main.dto.controller.auth.email.response.SendEmailResponseDto;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthSendVerifyEmailService {

    private final UserVerifyProperty userVerifyProperty;

    public SendEmailResponseDto sendVerifyEmail(SendEmailRequestDto sendEmailRequestDto) throws IOException {

        Map<String, Object> map = UnivCert.certify(
                userVerifyProperty.getKey(),
                sendEmailRequestDto.getEmail(),
                sendEmailRequestDto.getUniversityName(),
                false);

        return SendEmailResponseDto.builder()
                .apiResponse(map)
                .build();
    }

}
