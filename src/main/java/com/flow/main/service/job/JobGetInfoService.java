package com.flow.main.service.job;

import com.flow.main.dto.controller.job.get.info.JobGetInfoResponseDto;
import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.service.job.persistence.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobGetInfoService {

    private final JobService jobService;

    public JobGetInfoResponseDto getInfo(Long jobId){
        JobDto jobDto = jobService.findByJobId(jobId);
        return JobGetInfoResponseDto.builder()
            .jobId(jobDto.getJobId())
            .title(jobDto.getTitle())
            .subtitle(jobDto.getSubtitle())
            .description(jobDto.getDescription())
            .imageUrl(jobDto.getImageUrl())
            .redirectUrl(jobDto.getRedirectUrl())
            .build();
    }

}
