package com.flow.main.service.univcert;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.auth.code.request.VerifyCodeRequestDto;
import com.flow.main.dto.controller.auth.email.request.SendEmailRequestDto;
import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnivCertService {

    private final UserVerifyProperty userVerifyProperty;

    public Map<String, Object> sendVerifyEmail(SendEmailRequestDto sendEmailRequestDto){
        try{
            Map<String, Object> responseMap = UnivCert.certify(
                userVerifyProperty.getKey(),
                sendEmailRequestDto.getEmail(),
                sendEmailRequestDto.getUniversityName(),
                false);

            responseVerify(responseMap);
            return responseMap;
        } catch (IOException e) {
            log.error("IO Exception occurred while clearing user verification: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to clear user verification due to IO issue", e);
        }
    }

    public Map<String, Object> verifyCode(VerifyCodeRequestDto verifyCodeRequestDto){
        try{
            Map<String, Object> responseMap = UnivCert.certifyCode(
                userVerifyProperty.getKey(),
                verifyCodeRequestDto.getEmail(),
                verifyCodeRequestDto.getUniversityName(),
                verifyCodeRequestDto.getCode());

            responseVerify(responseMap);
            return responseMap;
        } catch (IOException e) {
            log.error("IO Exception occurred while clearing user verification: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to clear user verification due to IO issue", e);
        }
    }

    public void clearVerifySpecificUser(String email){
        try{
            Map<String, Object> responseMap = UnivCert.clear(userVerifyProperty.getKey(), email);

            responseVerify(responseMap);
        } catch (IOException e) {
            log.error("IO Exception occurred while clearing user verification: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to clear user verification due to IO issue", e);
        }
    }

    public void responseVerify(Map<String, Object> responseMap){
        Optional.ofNullable(responseMap)
            .filter(map -> !map.isEmpty())
            .ifPresentOrElse(
                map -> {
                    map.forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));
                    if(Boolean.FALSE.equals(map.get("success"))){
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST); }},
                () -> log.info("Response map is empty or null"));
    }

}
