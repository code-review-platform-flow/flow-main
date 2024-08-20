package com.flow.main.dto.controller.user.get.education.response;

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
public class EducationGetResponseDto {

    private String schoolName;
    private Year startDate;
    private Year endDate;

}
