package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.usersessions.UserSessionsDto;
import com.flow.main.entity.UserSessionsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserSessionsMapper extends GenericMapper<UserSessionsDto, UserSessionsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    UserSessionsEntity toEntity(UserSessionsDto userSessionsDto);

    @Mapping(source = "user.userId", target = "userId")
    UserSessionsDto toDto(UserSessionsEntity userSessionsEntity);

}
