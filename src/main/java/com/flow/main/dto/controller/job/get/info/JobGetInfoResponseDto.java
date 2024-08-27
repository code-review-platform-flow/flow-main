package com.flow.main.dto.controller.job.get.info;

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
public class JobGetInfoResponseDto {

    private Long jobId;
    private String title;
    private String subtitle;
    private String description;
    private String imageUrl;
    private String redirectUrl;

}
