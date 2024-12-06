package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.follows.FollowsDto;
import com.flow.main.entity.FollowsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowsMapper extends GenericMapper<FollowsDto, FollowsEntity> {
    List<FollowsDto> toDtoList(List<FollowsEntity> followsEntities);
}
