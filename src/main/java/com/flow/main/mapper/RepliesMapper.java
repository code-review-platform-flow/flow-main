package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.replies.RepliesDto;
import com.flow.main.entity.RepliesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepliesMapper extends GenericMapper<RepliesDto, RepliesEntity> {

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "commentId", target = "comment.commentId")
    RepliesEntity toEntity(RepliesDto repliesDto);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "comment.commentId", target = "commentId")
    RepliesDto toDto(RepliesEntity repliesEntity);

}
