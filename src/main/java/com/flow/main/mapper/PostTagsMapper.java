package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.MajorEntity;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostTagsMapper extends GenericMapper<PostTagsDto, PostTagsEntity> {

    @Mapping(source = "postId", target = "post.postId")
    @Mapping(source = "tagId", target = "tag.tagId")
    PostTagsEntity toEntity(PostTagsDto postTagsDto);

}
