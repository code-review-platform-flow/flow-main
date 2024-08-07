package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostsMapper extends GenericMapper<PostsDto, PostsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    PostsEntity toEntity(PostsDto postsDto);

}
