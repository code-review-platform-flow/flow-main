package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.entity.CareerEntity;
import com.flow.main.entity.EducationEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EducationMapper extends GenericMapper<EducationDto, EducationEntity> {

    @Mapping(source = "userInfo.userInfoId", target = "userInfoId")
    EducationDto toDto(EducationEntity educationEntity);

    @Mapping(source = "userInfoId", target = "userInfo.userInfoId")
    EducationEntity toEntity(EducationDto educationDto);

    @Mapping(source = "userInfo.userInfoId", target = "userInfoId")
    List<EducationDto> toListDto(List<EducationEntity> careerEntities);

    @Mapping(source = "userInfoId", target = "userInfo.userInfoId")
    List<EducationEntity> toListEntity(List<EducationDto> careerDto);

}
