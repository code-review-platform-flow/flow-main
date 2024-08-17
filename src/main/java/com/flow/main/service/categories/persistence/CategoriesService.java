package com.flow.main.service.categories.persistence;

import com.flow.main.dto.jpa.categories.CategoriesDto;
import com.flow.main.mapper.CategoriesMapper;
import com.flow.main.repository.CategoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;

    @Transactional(readOnly = true)
    public CategoriesDto findByCategoryName(String categoryName){
        return categoriesMapper.toDto(categoriesRepository.findByCategoryName(categoryName)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with categoryName : " + categoryName)));
    }

    @Transactional(readOnly = true)
    public CategoriesDto findByCategoryId(Long categoryId){
        return categoriesMapper.toDto(categoriesRepository.findByCategoryId(categoryId)
            .orElseThrow(() -> new EntityNotFoundException("Category not found with categoryId : " + categoryId)));
    }

    @Transactional(readOnly = true)
    public CategoriesDto findCategoryByKeyword(String keyword){
        return categoriesMapper.toDto(categoriesRepository.searchByKeyword(keyword)
            .orElse(null));
    }

}
