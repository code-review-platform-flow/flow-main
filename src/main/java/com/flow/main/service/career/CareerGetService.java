package com.flow.main.service.career;

import com.flow.main.dto.controller.user.get.career.response.CareerGetResponseDto;
import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.service.career.persistence.CareerService;
import java.time.Year;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerGetService {

    private final CareerService careerService;

    public CareerGetResponseDto getCareer(Long careerId){
        CareerDto careerDto = careerService.findByCareerId(careerId);

        Year responseStartDate = Year.of(careerDto.getStartDate().getYear());
        Year responseEndDate = Optional.ofNullable(careerDto.getEndDate())
            .map(date -> Year.of(date.getYear()))
            .orElse(null);

        return CareerGetResponseDto.builder()
            .title(careerDto.getTitle())
            .description(careerDto.getDescription())
            .startDate(responseStartDate)
            .endDate(responseEndDate)
            .build();
    }

}
