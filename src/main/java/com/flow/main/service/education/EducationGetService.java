package com.flow.main.service.education;

import com.flow.main.dto.controller.user.get.education.response.EducationGetResponseDto;
import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.service.education.persistence.EducationService;
import java.time.Year;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationGetService {

    private final EducationService educationService;

    public EducationGetResponseDto getEducation(Long educationId){

        EducationDto educationDto = educationService.findByEducationId(educationId);

        Year responseStartDate = Year.of(educationDto.getStartDate().getYear());
        Year responseEndDate = Optional.ofNullable(educationDto.getEndDate())
            .map(date -> Year.of(date.getYear()))
            .orElse(null);

        return EducationGetResponseDto.builder()
            .schoolName(educationDto.getSchoolName())
            .startDate(responseStartDate)
            .endDate(responseEndDate)
            .build();
    }
}
