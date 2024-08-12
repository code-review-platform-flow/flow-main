package com.flow.main.service.auth;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.code.request.VerifyCodeRequestDto;
import com.flow.main.dto.controller.auth.code.response.VerifyCodeResponseDto;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthVerifyCodeService {

    private final UserVerifyProperty userVerifyProperty;

    public VerifyCodeResponseDto verifyCode(VerifyCodeRequestDto verifyCodeRequestDto) throws IOException {
        Map<String, Object> map = UnivCert.certifyCode(
                userVerifyProperty.getKey(),
                verifyCodeRequestDto.getEmail(),
                verifyCodeRequestDto.getUniversityName(),
                verifyCodeRequestDto.getCode());

        return VerifyCodeResponseDto.builder()
                .apiResponse(map)
                .build();
    }
}
