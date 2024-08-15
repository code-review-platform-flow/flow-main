package com.flow.main.service.major.persistence;

import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.mapper.MajorMapper;
import com.flow.main.repository.MajorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;
    private final MajorMapper majorMapper;

    @Transactional(readOnly = true)
    public List<MajorDto> getAllMajor(){
        return majorMapper.toDtoList(majorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public MajorDto findByMajorName(String majorName){
        return majorMapper.toDto(majorRepository.findByMajorName(majorName)
            .orElseThrow(() -> new EntityNotFoundException("Major not found with majorName : " + majorName)));
    }

    @Transactional(readOnly = true)
    public MajorDto findByMajorId(Long majorId){
        return majorMapper.toDto(majorRepository.findByMajorId(majorId)
            .orElseThrow(() -> new EntityNotFoundException("Major not found with majorId : " + majorId)));
    }
}
