package com.flow.main.service.education.persistence;

import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.entity.EducationEntity;
import com.flow.main.mapper.EducationMapper;
import com.flow.main.repository.EducationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    @Transactional
    public EducationDto save(EducationDto educationDto){
        EducationEntity educationEntity = educationMapper.toEntity(educationDto);
        return educationMapper.toDto(educationRepository.save(educationEntity));
    }

    @Transactional(readOnly = true)
    public EducationDto findByEducationId(Long educationId){
        return educationMapper.toDto(educationRepository.findByEducationId(educationId)
            .orElseThrow(() -> new EntityNotFoundException("Education not found with educationId : " + educationId)));
    }

    @Transactional(readOnly = true)
    public List<EducationDto> findAllByUserInfoId(Long userInfoId){
        return educationMapper.toListDto(educationRepository.findAllByUserInfoId(userInfoId)
            .orElse(Collections.emptyList()));
    }

    @Transactional
    public void delete(EducationDto educationDto){
        EducationEntity educationEntity = educationMapper.toEntity(educationDto);
        educationEntity.markDeleted();
        educationRepository.save(educationEntity);
    }

}
