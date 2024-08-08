package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.comments.CommentsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.entity.CommentsEntity;
import com.flow.main.entity.PostTagsEntity;
import com.flow.main.entity.RepliesEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentsMapper extends GenericMapper<CommentsDto, CommentsEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "postId", target = "post.postId")
    CommentsEntity toEntity(CommentsDto commentsDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "post.postId", target = "postId")
    CommentsDto toDto(CommentsEntity commentsEntity);

}
