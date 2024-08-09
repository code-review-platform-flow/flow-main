package com.flow.main.service.users;

import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.mapper.UsersMapper;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersCreateService {

    private final UsersService usersService;
    private final UsersMapper usersMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersDto create(RegisterRequestDto registerRequestDto){
        UsersDto usersDto = UsersDto.builder()
            .email(registerRequestDto.getEmail())
            .password(bCryptPasswordEncoder.encode(registerRequestDto.getPassword()))
            .build();
        return usersService.save(usersDto);

    }
}
