package com.flow.main.dto.controller.user.update.career.request;

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
public class CareerUpdateRequestDto {

    private Long careerId;
    private String email;
    private Year startDate;
    private Year endDate;
    private String description;
    private String title;

}
