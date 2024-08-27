package com.flow.main.service.job;

import com.flow.main.dto.controller.job.get.JobIdDto;
import com.flow.main.dto.controller.job.get.all.JobGetAllResponseDto;
import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.service.job.persistence.JobService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobGetAllService {

    private final JobService jobService;

    public JobGetAllResponseDto getAll(){
        List<JobDto> jobDtoList = jobService.findAll();
        List<JobIdDto> jobIdDtoList = jobDtoList.stream().map(jobDto ->
                JobIdDto.builder().jobId(jobDto.getJobId()).build()).toList();
        return JobGetAllResponseDto.builder()
            .jobIdList(jobIdDtoList)
            .build();
    }

}