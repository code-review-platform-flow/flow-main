package com.flow.main.service.categories.persistence;

import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.mapper.CategoriesMapper;
import com.flow.main.repository.CategoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;

    public CategoriesDto findByCategoryName(String categoryName){
        return categoriesMapper.toDto(categoriesRepository.findByCategoryName(categoryName)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with categoryName : " + categoryName)));
    }

}
