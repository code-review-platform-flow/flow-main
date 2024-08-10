package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.dto.jpa.posts.PostsDto;
import com.flow.main.entity.LikesEntity;
import com.flow.main.entity.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikesMapper extends GenericMapper<LikesDto, LikesEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "postId", target = "post.postId")
    LikesEntity toEntity(LikesDto postsDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "post.postId", target = "postId")
    @Mapping(source = "useYn", target = "useYn")
    LikesDto toDto(LikesEntity likesEntity);

}
