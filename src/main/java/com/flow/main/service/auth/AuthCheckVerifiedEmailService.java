package com.flow.main.service.auth;

import com.flow.main.common.property.UserVerifyProperty;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthCheckVerifiedEmailService {

    private final UserVerifyProperty userVerifyProperty;

    public void checkVerifiedEmail(String email) throws IOException {
        Map<String, Object> map = UnivCert.status(userVerifyProperty.getKey(), email);
        if(Boolean.FALSE.equals(map.get("success"))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email is not verified");
        }
    }
}
