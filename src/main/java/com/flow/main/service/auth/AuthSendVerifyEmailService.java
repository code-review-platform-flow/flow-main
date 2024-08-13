package com.flow.main.service.auth;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.flow.main.dto.controller.auth.email.response.SendEmailResponseDto;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSendVerifyEmailService {

    private final UserVerifyProperty userVerifyProperty;

    public SendEmailResponseDto sendVerifyEmail(SendEmailRequestDto sendEmailRequestDto) throws IOException {

        Map<String, Object> map = UnivCert.certify(
                userVerifyProperty.getKey(),
                sendEmailRequestDto.getEmail(),
                sendEmailRequestDto.getUniversityName(),
                false);

        if (map != null && !map.isEmpty()) {
            map.forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));
        } else {
            log.info("Map is empty or null");
        }

        return SendEmailResponseDto.builder()
                .apiResponse(map)
                .build();
    }

}
