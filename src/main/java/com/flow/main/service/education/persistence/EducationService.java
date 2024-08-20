package com.flow.main.service.education.persistence;

import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.mapper.EducationMapper;
import com.flow.main.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

}
