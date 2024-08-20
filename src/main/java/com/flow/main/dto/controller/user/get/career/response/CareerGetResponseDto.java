package com.flow.main.dto.controller.user.get.career.response;

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
public class CareerGetResponseDto {

    private String title;
    private String description;
    private Year startDate;
    private Year endDate;

}
