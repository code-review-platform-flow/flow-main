package com.flow.main.service.tags.persistence;

import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.entity.TagsEntity;
import com.flow.main.mapper.TagsMapper;
import com.flow.main.repository.TagsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagsService {

    private final TagsRepository tagsRepository;
    private final TagsMapper tagsMapper;

    @Transactional
    public TagsDto saveOrFindByTagName(String tagName){
        boolean check = tagsRepository.existsByTagName(tagName);
        if(!check)
            return tagsMapper.toDto(tagsRepository.save(TagsEntity.builder().tagName(tagName).build()));
        else return findByTagName(tagName);
    }

    @Transactional(readOnly = true)
    public TagsDto findByTagName(String tagName){
        return tagsMapper.toDto(tagsRepository.findByTagName(tagName)
            .orElseThrow(() -> new EntityNotFoundException("Tag not found with tagName : " + tagName)));
    }

    @Transactional(readOnly = true)
    public TagsDto findByTagId(Long tagId){
        return tagsMapper.toDto(tagsRepository.findByTagId(tagId)
            .orElseThrow(() -> new EntityNotFoundException("Tag not found with tagId : " + tagId)));
    }

    @Transactional(readOnly = true)
    public List<TagsDto> findTagsByKeyword(String keyword){
        return tagsMapper.toDtoList(tagsRepository.searchByKeyword(keyword)
            .orElse(Collections.emptyList()));
    }
}
