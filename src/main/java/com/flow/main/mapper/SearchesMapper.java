package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.searches.SearchesDto;
import com.flow.main.entity.SearchesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SearchesMapper extends GenericMapper<SearchesDto, SearchesEntity> {

    @Mapping(source = "user.userId", target = "userId")
    SearchesDto toDto(SearchesEntity searchesEntity);

    @Mapping(source = "userId", target = "user.userId")
    SearchesEntity toEntity(SearchesDto searchesDto);

}
