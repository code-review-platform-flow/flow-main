package com.flow.main.dto.jpa.education;

import com.flow.main.entity.UserInfoEntity;
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
public class EducationDto {

    private Long educationId;
    private Long userInfoId;
    private String schoolName;
    private Year startDate;
    private Year endDate;
    private int version;
    
}
