package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.PostsEntity;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostTagsMapper.class})
public interface PostsMapper extends GenericMapper<PostsDto, PostsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(source = "postTagsDtos", target = "postTagsEntities")
    PostsEntity toEntity(PostsDto postsDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "postTagsEntities", target = "postTagsDtos")
    PostsDto toDto(PostsEntity postsEntity);

    @Mapping(source = "post.postId", target = "postId")
    @Mapping(source = "tag.tagId", target = "tagId")
    List<PostTagsDto> toListDto(List<PostTagsEntity> postTagsEntities);

}
