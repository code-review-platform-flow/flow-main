package com.flow.main.service.school.persistence;

import com.flow.main.dto.jpa.school.SchoolDto;
import com.flow.main.mapper.SchoolMapper;
import com.flow.main.repository.SchoolRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Transactional(readOnly = true)
    public SchoolDto findBySchoolName(String schoolName){
        return schoolMapper.toDto(schoolRepository.findBySchoolName(schoolName)
            .orElseThrow(() -> new EntityNotFoundException("School not found with schoolName : " + schoolName)));
    }
}
