package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.userinfo.UserInfoDto;
import com.flow.main.dto.usersessions.UserSessionsDto;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.entity.UserSessionsEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserSessionsMapper extends GenericMapper<UserSessionsDto, UserSessionsEntity> {

}
