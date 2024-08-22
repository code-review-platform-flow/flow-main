package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.hof.HallOfFameDto;
import com.flow.main.dto.jpa.likes.LikesDto;
import com.flow.main.entity.HallOfFameEntity;
import com.flow.main.entity.LikesEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HallOfFameMapper extends GenericMapper<HallOfFameDto, HallOfFameEntity> {

    @Mapping(source = "user.userId", target = "userId")
    HallOfFameDto toDto(HallOfFameEntity hallOfFameEntity);

    @Mapping(source = "userId", target = "user.userId")
    HallOfFameEntity toEntity(HallOfFameDto hallOfFameDto);

    @Mapping(source = "user.userId", target = "userId")
    List<HallOfFameDto> toDtoList(List<HallOfFameEntity> hallOfFameEntities);

    @Mapping(source = "userId", target = "user.userId")
    List<HallOfFameEntity> toEntityList(List<HallOfFameDto> hallOfFameDtos);

}
