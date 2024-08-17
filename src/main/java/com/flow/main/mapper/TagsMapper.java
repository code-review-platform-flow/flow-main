package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.PostsEntity;
import com.flow.main.entity.TagsEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagsMapper extends GenericMapper<TagsDto, TagsEntity> {

    List<TagsEntity> toEntityList(List<TagsDto> tagsDto);
    List<TagsDto> toDtoList(List<TagsEntity> tagsDto);
}
