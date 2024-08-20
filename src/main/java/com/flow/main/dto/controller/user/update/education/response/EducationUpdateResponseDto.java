package com.flow.main.dto.controller.user.update.education.response;

import java.time.LocalDate;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationUpdateResponseDto {

    private String schoolName;
    private Year startDate;
    private Year endDate;

}
