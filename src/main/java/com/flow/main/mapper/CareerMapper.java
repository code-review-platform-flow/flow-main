package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.entity.CareerEntity;
import java.io.CharArrayReader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CareerMapper extends GenericMapper<CareerDto, CareerEntity> {

    @Mapping(source = "userInfo.userInfoId", target = "userInfoId")
    CareerDto toDto(CareerEntity careerEntity);

    @Mapping(source = "userInfoId", target = "userInfo.userInfoId")
    CareerEntity toEntity(CareerDto careerDto);

}
