package com.flow.main.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.entity.AlarmEntity;

@Mapper(componentModel = "spring")
public interface AlarmMapper extends GenericMapper<AlarmDto, AlarmEntity> {

	@Mapping(source = "userId", target = "user.userId")
	AlarmEntity toEntity(AlarmDto alarmDto);

	@Mapping(source = "user.userId", target = "userId")
	AlarmDto toDto(AlarmEntity alarmEntity);

	List<AlarmEntity> toListEntity(List<AlarmDto> alarmDtoList);

	List<AlarmDto> toListDto(List<AlarmEntity> alarmEntityList);

}
