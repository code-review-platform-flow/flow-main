package com.flow.main.mapper;

import com.flow.main.dto.controller.auth.register.request.RegisterRequestDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UsersEntity toUsersEntity(RegisterRequestDto dto);

    @Mapping(source = "dto.studentNumber", target = "studentNumber")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "school", target = "school")
    @Mapping(source = "major", target = "major")
    @Mapping(target = "version", ignore = true)
    UserInfoEntity toUserInfoEntity(UsersEntity user, SchoolEntity school, MajorEntity major, RegisterRequestDto dto);
}
