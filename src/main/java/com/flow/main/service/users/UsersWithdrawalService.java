package com.flow.main.service.users;

import com.flow.main.common.property.UserVerifyProperty;
import com.flow.main.dto.controller.user.withdrawal.request.UserWithdrawalRequestDto;
import com.flow.main.dto.controller.user.withdrawal.response.UserWithdrawalResponseDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.dto.jpa.withdrawal.WithdrawalDto;
import com.flow.main.service.univcert.UnivCertService;
import com.flow.main.service.users.persistence.UsersService;
import com.flow.main.service.withdrawal.persistence.WithdrawalService;
import com.univcert.api.UnivCert;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersWithdrawalService {

    private final UnivCertService univCertService;
    private final UsersService usersService;
    private final WithdrawalService withdrawalService;

    public UserWithdrawalResponseDto withdrawal(UserWithdrawalRequestDto userWithdrawalRequestDto){
        UsersDto usersDto = usersService.findByEmail(userWithdrawalRequestDto.getEmail());
        withdrawalService.save(WithdrawalDto.builder()
            .userId(usersDto.getUserId())
            .reason(userWithdrawalRequestDto.getReason())
            .build());
        usersService.delete(usersDto);
        univCertService.clearVerifySpecificUser(userWithdrawalRequestDto.getEmail());

        return UserWithdrawalResponseDto.builder().build();
    }
}
