package com.flow.main.service.withdrawal.persistence;

import com.flow.main.dto.jpa.withdrawal.WithdrawalDto;
import com.flow.main.entity.WithdrawalEntity;
import com.flow.main.mapper.WithdrawalMapper;
import com.flow.main.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final WithdrawalMapper withdrawalMapper;

    @Transactional
    public WithdrawalDto save(WithdrawalDto withdrawalDto){
        WithdrawalEntity withdrawalEntity = withdrawalMapper.toEntity(withdrawalDto);
        return withdrawalMapper.toDto(withdrawalRepository.save(withdrawalEntity));
    }

}
