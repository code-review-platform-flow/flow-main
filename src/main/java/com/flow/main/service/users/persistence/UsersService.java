package com.flow.main.service.users.persistence;

import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.mapper.UsersMapper;
import com.flow.main.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Transactional
    public UsersDto save(UsersDto usersDto){
        return usersMapper.toDto(usersRepository.save(usersMapper.toEntity(usersDto)));
    }

    public boolean existsByEmail(String email){
        return usersRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public UsersDto findByEmail(String email){
        return usersMapper.toDto(usersRepository.findByEmail(email).orElseThrow(
            () -> new EntityNotFoundException("User not found with email : " + email)));
    }

    @Transactional(readOnly = true)
    public UsersDto findByUserId(Long userId){
        return usersMapper.toDto(usersRepository.findByUserId(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with userId : " + userId)));
    }

}
