package com.flow.main.mapper;

import com.flow.main.dto.request.RegisterRequestDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserEntity;
import com.flow.main.entity.UserInfoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserEntity toUserEntity(RegisterRequestDto dto);

    @Mapping(source = "dto.studentNumber", target = "studentNumber")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "school", target = "school")
    @Mapping(source = "major", target = "major")
    @Mapping(target = "version", ignore = true)
    UserInfoEntity toUserInfoEntity(UserEntity user, SchoolEntity school, MajorEntity major, RegisterRequestDto dto);
}
