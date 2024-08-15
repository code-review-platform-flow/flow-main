package com.flow.main.mapper;


import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.controller.auth.major.MajorGetAllDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.entity.MajorEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MajorMapper extends GenericMapper<MajorDto, MajorEntity> {

    @Mapping(source = "majorId", target = "majorId")
    List<MajorDto> toDtoList(List<MajorEntity> majorEntities);

    @Mapping(source = "majorId", target = "majorId")
    @Mapping(source = "majorName", target = "majorName")
    List<MajorGetAllDto> toMajorGetAllDtoList(List<MajorDto> majorDtos);

}
