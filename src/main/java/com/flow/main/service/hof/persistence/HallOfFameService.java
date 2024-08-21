package com.flow.main.service.hof.persistence;

import com.flow.main.dto.jpa.hof.HallOfFameDto;
import com.flow.main.entity.HallOfFameEntity;
import com.flow.main.mapper.HallOfFameMapper;
import com.flow.main.repository.HallOfFameRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HallOfFameService {

    private final HallOfFameRepository hallOfFameRepository;
    private final HallOfFameMapper hallOfFameMapper;

    @Transactional
    public HallOfFameDto save(HallOfFameDto hallOfFameDto){
        HallOfFameEntity hallOfFameEntity = hallOfFameMapper.toEntity(hallOfFameDto);
        return hallOfFameMapper.toDto(hallOfFameRepository.save(hallOfFameEntity));
    }

    @Transactional(readOnly = true)
    public List<HallOfFameDto> findAllByDateAwarded(){
        LocalDate dateAwarded = LocalDate.now(ZoneId.of("Asia/Seoul"));
        return hallOfFameMapper.toDtoList(hallOfFameRepository.findAllByDateAwarded(dateAwarded)
            .orElse(Collections.emptyList()));
    }

}
