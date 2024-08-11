package com.flow.main.mapper;


import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.entity.UserInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInfoMapper extends GenericMapper<UserInfoDto, UserInfoEntity> {

    @Mapping(source = "majorId", target = "major.majorId")
    @Mapping(source = "schoolId", target = "school.schoolId")
    @Mapping(source = "userId", target = "user.userId")
    UserInfoEntity toEntity(UserInfoDto userInfoDto);

    @Mapping(source = "major.majorId", target = "majorId")
    @Mapping(source = "school.schoolId", target = "schoolId")
    @Mapping(source = "user.userId", target = "userId")
    UserInfoDto toDto(UserInfoEntity userInfoEntity);

}
