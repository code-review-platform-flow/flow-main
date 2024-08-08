package com.flow.main.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.coffeeChats.CoffeeChatsDto;
import com.flow.main.dto.jpa.posttags.PostTagsDto;
import com.flow.main.entity.CoffeeChatsEntity;
import com.flow.main.entity.PostTagsEntity;

@Mapper(componentModel = "spring")
public interface CoffeeChatsMapper extends GenericMapper<CoffeeChatsDto, CoffeeChatsEntity> {

	@Mapping(target = "initiatorUserId", source = "initiatorUser.userId")
	@Mapping(target = "recipientUserId", source = "recipientUser.userId")
	CoffeeChatsDto toDto(CoffeeChatsEntity coffeeChatsEntity);

	@Mapping(target = "initiatorUser.userId", source = "initiatorUserId")
	@Mapping(target = "recipientUser.userId", source = "recipientUserId")
	CoffeeChatsEntity toEntity(CoffeeChatsDto coffeeChatsDto);

	@Mapping(target = "initiatorUserId", source = "initiatorUser.userId")
	@Mapping(target = "recipientUserId", source = "recipientUser.userId")
	List<CoffeeChatsDto> toListDto(List<CoffeeChatsEntity> coffeeChatsEntities);

	@Mapping(target = "initiatorUser.userId", source = "initiatorUserId")
	@Mapping(target = "recipientUser.userId", source = "recipientUserId")
	List<CoffeeChatsEntity> toListEntity(List<CoffeeChatsDto> coffeeChatsDtoList);

}
