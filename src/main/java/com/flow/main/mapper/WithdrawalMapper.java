package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.dto.jpa.withdrawal.WithdrawalDto;
import com.flow.main.entity.LikesEntity;
import com.flow.main.entity.WithdrawalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WithdrawalMapper extends GenericMapper<WithdrawalDto, WithdrawalEntity> {

    @Mapping(source = "user.userId", target = "userId")
    WithdrawalDto toDto(WithdrawalEntity withdrawalEntity);

    @Mapping(source = "userId", target = "user.userId")
    WithdrawalEntity toEntity(WithdrawalDto withdrawalDto);
}
