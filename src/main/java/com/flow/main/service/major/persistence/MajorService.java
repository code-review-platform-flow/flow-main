package com.flow.main.service.major.persistence;

import com.flow.main.dto.major.MajorDto;
import com.flow.main.mapper.MajorMapper;
import com.flow.main.repository.MajorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;
    private final MajorMapper majorMapper;

    public MajorDto findByMajorName(String majorName){
        return majorMapper.toDto(majorRepository.findByMajorName(majorName)
            .orElseThrow(() -> new EntityNotFoundException("Major not found with majorName : " + majorName)));
    }
}
