package com.flow.main.dto.controller.user.update.education.request;

import java.time.LocalDate;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationUpdateRequestDto {

    private String email;
    private Long educationId;
    private String schoolName;
    private Year startDate;
    private Year endDate;

}
