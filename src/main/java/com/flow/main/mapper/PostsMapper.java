package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.CommentsEntity;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.PostsEntity;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostsMapper extends GenericMapper<PostsDto, PostsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    PostsEntity toEntity(PostsDto postsDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "createDate", target = "createDate")
    PostsDto toDto(PostsEntity postsEntity);

}
