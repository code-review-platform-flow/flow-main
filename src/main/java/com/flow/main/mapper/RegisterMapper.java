package com.flow.main.mapper;

import com.flow.main.dto.request.RegisterDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserEntity;
import com.flow.main.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserEntity registerDtoToUserEntity(RegisterDto dto);

    @Mapping(source = "schoolName", target = "schoolName")
    SchoolEntity registerDtoToSchoolEntity(RegisterDto dto);

    @Mapping(source = "majorName", target = "majorName")
    MajorEntity registerDtoToMajorEntity(RegisterDto dto);

    @Mapping(source = "dto.studentNumber", target = "studentNumber")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "school", target = "school")
    @Mapping(source = "major", target = "major")
    @Mapping(target = "version", ignore = true)
    UserInfoEntity registerDtoToUserInfoEntity(UserEntity user, SchoolEntity school, MajorEntity major, RegisterDto dto);

}
