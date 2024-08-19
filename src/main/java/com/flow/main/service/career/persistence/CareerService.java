package com.flow.main.service.career.persistence;

import com.flow.main.mapper.CareerMapper;
import com.flow.main.repository.CareerRepository;
import com.flow.main.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CareerService {

    public final CareerRepository careerRepository;
    public final CareerMapper careerMapper;


}
