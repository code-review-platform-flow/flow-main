package com.flow.main.service.auth;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.flow.main.dto.controller.auth.email.response.SendEmailResponseDto;
import com.flow.main.service.univcert.UnivCertService;
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

    private final UnivCertService univCertService;

    public SendEmailResponseDto sendVerifyEmail(SendEmailRequestDto sendEmailRequestDto) {

        Map<String, Object> result = univCertService.sendVerifyEmail(sendEmailRequestDto);

        return SendEmailResponseDto.builder()
                .apiResponse(result)
                .build();
    }

}
