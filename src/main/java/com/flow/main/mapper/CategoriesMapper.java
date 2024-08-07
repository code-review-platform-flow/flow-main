package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.CategoriesEntity;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriesMapper extends GenericMapper<CategoriesDto, CategoriesEntity> {

}
