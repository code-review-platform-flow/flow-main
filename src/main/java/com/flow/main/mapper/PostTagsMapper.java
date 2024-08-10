package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.PostsEntity;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.Query;

@Mapper(componentModel = "spring")
public interface PostTagsMapper extends GenericMapper<PostTagsDto, PostTagsEntity> {

    @Mapping(source = "postId", target = "post.postId")
    @Mapping(source = "tagId", target = "tag.tagId")
    PostTagsEntity toEntity(PostTagsDto postTagsDto);

    @Mapping(source = "post.postId", target = "postId")
    @Mapping(source = "tag.tagId", target = "tagId")
    PostTagsDto toDto(PostTagsEntity postTagsEntity);

    @Mapping(source = "post.postId", target = "postId")
    @Mapping(source = "tag.tagId", target = "tagId")
    List<PostTagsDto> toListDto(List<PostTagsEntity> postTagsEntities);

    @Mapping(source = "post.postId", target = "postId")
    @Mapping(source = "tag.tagId", target = "tagId")
    List<PostTagsEntity> toListEntity(List<PostTagsDto> postTagsDtos);
}
