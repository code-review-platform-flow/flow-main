package com.flow.main.service.coffeeChats.persistence;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flow.main.dto.jpa.coffeeChats.CoffeeChatsDto;
import com.flow.main.entity.CoffeeChatsEntity;
import com.flow.main.mapper.CoffeeChatsMapper;
import com.flow.main.repository.CoffeeChatsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoffeeChatsService {

	private final CoffeeChatsRepository coffeeChatsRepository;
	private final CoffeeChatsMapper coffeeChatsMapper;

	@Transactional(readOnly = true)
	public List<CoffeeChatsDto> getAllByInitiatorUserIdWithPageable(Long initiatorUserId, Pageable pageable) {
		return coffeeChatsMapper.toListDto(coffeeChatsRepository.findAllCoffeeChatsByInitiatorUserIdWithPageable(initiatorUserId, pageable));
	}

    @Transactional(readOnly = true)
	public List<CoffeeChatsDto> getAllByRecipientUserIdWithPageable(Long recipientUserId, Pageable pageable) {
		return coffeeChatsMapper.toListDto(coffeeChatsRepository.findAllCoffeeChatsByRecipientUserIdWithPageable(recipientUserId, pageable));
	}

	@Transactional
	public CoffeeChatsDto save(CoffeeChatsDto coffeeChatsDto) {
		CoffeeChatsEntity coffeeChatsEntity = coffeeChatsMapper.toEntity(coffeeChatsDto);
		coffeeChatsRepository.save(coffeeChatsEntity);
		return coffeeChatsMapper.toDto(coffeeChatsEntity);
	}

}
