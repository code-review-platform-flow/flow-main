package com.flow.main.service.usersessions;

import com.flow.main.dto.controller.auth.logout.response.LogoutResponseDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.service.security.JwtUtil;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSessionsLogoutService {

    private final UserSessionsService userSessionsService;
    private final JwtUtil jwtUtil;

    public LogoutResponseDto logout(String accessToken){
        jwtUtil.checkValidToken(accessToken);
        String token = jwtUtil.getToken(accessToken);

        UserSessionsDto userSessionsDto = userSessionsService.findByAccessToken(token);
        userSessionsService.delete(userSessionsDto);

        return LogoutResponseDto.builder().build();
    }

}
