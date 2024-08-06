package com.flow.main.mapper;


import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.entity.MajorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MajorMapper extends GenericMapper<MajorDto, MajorEntity> {


}
