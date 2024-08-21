package com.flow.main.service.univcert;

import com.flow.main.common.property.UserVerifyProperty;
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

            Optional.ofNullable(responseMap)
                .filter(map -> !map.isEmpty())
                .ifPresentOrElse(
                    map -> {
                        map.forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));
                        if(Boolean.FALSE.equals(map.get("success"))){
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                        }
                    },
                    () -> log.info("Response map is empty or null")
                );

            return responseMap;
        } catch (IOException e) {
            log.error("IO Exception occurred while clearing user verification: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to clear user verification due to IO issue", e);
        } catch (ResponseStatusException e) {
            log.error("Response status exception: {}", e.getMessage(), e);
            throw e;
        }
    }

    public void clearVerifySpecificUser(String email){
        try{
            Map<String, Object> result = UnivCert.clear(userVerifyProperty.getKey(), email);
            if (Boolean.FALSE.equals(result.get("success"))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User verification clear failed");
            }
        } catch (IOException e) {
            log.error("IO Exception occurred while clearing user verification: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to clear user verification due to IO issue", e);
        } catch (ResponseStatusException e) {
            log.error("Response status exception: {}", e.getMessage(), e);
            throw e;
        }
    }

}
