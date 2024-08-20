package com.flow.main.dto.jpa.career;

import java.time.LocalDate;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerDto {

    private Long careerId;
    private Long userInfoId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String title;
    private int version;

}
