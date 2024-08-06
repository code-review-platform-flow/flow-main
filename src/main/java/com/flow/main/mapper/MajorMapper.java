package com.flow.main.mapper;


import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.major.MajorDto;
import com.flow.main.dto.school.SchoolDto;
import com.flow.main.dto.userinfo.UserInfoDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserInfoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MajorMapper extends GenericMapper<MajorDto, MajorEntity> {

}
