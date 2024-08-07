package com.flow.main.service.tags.persistence;

import com.flow.main.dto.jpa.tags.TagsDto;
import com.flow.main.entity.TagsEntity;
import com.flow.main.mapper.TagsMapper;
import com.flow.main.repository.TagsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagsService {

    private final TagsRepository tagsRepository;
    private final TagsMapper tagsMapper;

    public TagsDto saveOrFindByTagName(String tagName){
        boolean check = tagsRepository.existsByTagName(tagName);
        if(!check)
            return tagsMapper.toDto(tagsRepository.save(TagsEntity.builder().tagName(tagName).build()));
        else return findByTagName(tagName);
    }

    public TagsDto findByTagName(String tagName){
        return tagsMapper.toDto(tagsRepository.findByTagName(tagName)
            .orElseThrow(() -> new EntityNotFoundException("Tag not found with tagName : " + tagName)));
    }

}
