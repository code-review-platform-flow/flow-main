package com.flow.main.mapper;

import org.mapstruct.Mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.alarm.AlarmDto;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.entity.AlarmEntity;
import com.flow.main.entity.CategoriesEntity;

@Mapper(componentModel = "spring")
public interface AlarmMapper extends GenericMapper<AlarmDto, AlarmEntity> {
}
