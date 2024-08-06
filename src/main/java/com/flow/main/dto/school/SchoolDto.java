package com.flow.main.dto.school;

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
public class SchoolDto {
    private Long schoolId;
    private String schoolName;
    private String note;
    private int version;
}
