package com.flow.main.service.career.persistence;

import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.entity.CareerEntity;
import com.flow.main.mapper.CareerMapper;
import com.flow.main.repository.CareerRepository;
import com.flow.main.repository.CategoriesRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CareerService {

    public final CareerRepository careerRepository;
    public final CareerMapper careerMapper;

    @Transactional
    public CareerDto save(CareerDto careerDto){
        CareerEntity careerEntity = careerMapper.toEntity(careerDto);
        return careerMapper.toDto(careerRepository.save(careerEntity));
    }

    @Transactional(readOnly = true)
    public CareerDto findByCareerId(Long careerId){
        return careerMapper.toDto(careerRepository.findByCareerId(careerId)
            .orElseThrow(() -> new EntityNotFoundException("Career not found with careerId : " + careerId)));
    }

    @Transactional(readOnly = true)
    public List<CareerDto> findAllByUserInfoId(Long userInfoId){
        return careerMapper.toListDto(careerRepository.findAllByUserInfoId(userInfoId)
            .orElse(Collections.emptyList()));
    }

    @Transactional
    public void delete(CareerDto careerDto){
        CareerEntity careerEntity = careerMapper.toEntity(careerDto);
        careerEntity.markDeleted();
        careerRepository.save(careerEntity);
    }
}
