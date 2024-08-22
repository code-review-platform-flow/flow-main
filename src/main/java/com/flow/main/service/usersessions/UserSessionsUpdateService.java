package com.flow.main.service.usersessions;

import com.flow.main.dto.controller.auth.recreate.response.RecreateAccessTokenResponseDto;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.mapper.UserSessionsMapper;
import com.flow.main.service.security.JwtUtil;
import com.flow.main.service.usersessions.persistence.UserSessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSessionsUpdateService {

    private final UserSessionsService userSessionsService;
    private final JwtUtil jwtUtil;

    public RecreateAccessTokenResponseDto recreateAccessToken(String refreshToken){
        jwtUtil.checkValidToken(refreshToken);
        String token = jwtUtil.getToken(refreshToken);
        jwtUtil.isExpired(token);

        Long userId = jwtUtil.getUserId(token);
        String email = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);
        String accessToken = jwtUtil.createAccessToken(userId, email, role);

        UserSessionsDto userSessionsDto = userSessionsService.findByRefreshToken(token);
        userSessionsDto.setAccessToken(accessToken);
        UserSessionsDto savedUserSessionsDto = userSessionsService.save(userSessionsDto);

        return RecreateAccessTokenResponseDto.builder()
            .accessToken(savedUserSessionsDto.getAccessToken())
            .build();
    }

}
