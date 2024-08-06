package com.flow.main.mapper;


import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.school.SchoolDto;
import com.flow.main.dto.userinfo.UserInfoDto;
import com.flow.main.dto.users.UsersDto;
import com.flow.main.entity.SchoolEntity;
import com.flow.main.entity.UserInfoEntity;
import com.flow.main.entity.UsersEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.parameters.P;

@Mapper(componentModel = "spring")
public interface UserInfoMapper extends GenericMapper<UserInfoDto, UserInfoEntity> {

    @Mapping(source = "majorId", target = "major.majorId")
    @Mapping(source = "schoolId", target = "school.schoolId")
    @Mapping(source = "userId", target = "user.userId")
    UserInfoEntity toEntity(UserInfoDto userInfoDto);

}
