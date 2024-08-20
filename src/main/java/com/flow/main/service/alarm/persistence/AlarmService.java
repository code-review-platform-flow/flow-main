package com.flow.main.service.alarm.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.entity.AlarmEntity;
import com.flow.main.mapper.AlarmMapper;
import com.flow.main.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {

	private final AlarmRepository alarmRepository;
	private final AlarmMapper alarmMapper;

	public List<AlarmDto> getAllByUserId(Long userId) {
		return alarmMapper.toListDto(alarmRepository.findAllByUserId(userId));
	}

	public AlarmDto save(AlarmDto alarmDto) {
		AlarmEntity alarmEntity = alarmMapper.toEntity(alarmDto);
		alarmRepository.save(alarmEntity);
		return alarmMapper.toDto(alarmEntity);
	}

}
