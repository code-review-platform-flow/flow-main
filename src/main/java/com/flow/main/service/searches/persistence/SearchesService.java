package com.flow.main.service.searches.persistence;

import com.flow.main.dto.jpa.searches.SearchesDto;
import com.flow.main.entity.SearchesEntity;
import com.flow.main.mapper.SearchesMapper;
import com.flow.main.repository.SearchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchesService {

    private final SearchesRepository searchesRepository;
    private final SearchesMapper searchesMapper;

    @Transactional
    public SearchesDto save(SearchesDto searchesDto){
        SearchesEntity searchesEntity = searchesMapper.toEntity(searchesDto);
        return searchesMapper.toDto(searchesRepository.save(searchesEntity));
    }

}
