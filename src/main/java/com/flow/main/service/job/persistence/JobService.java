package com.flow.main.service.job.persistence;

import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.mapper.JobMapper;
import com.flow.main.repository.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public List<JobDto> findAll(){
        return jobMapper.toListDto(jobRepository.findAllJobs()
            .orElse(Collections.emptyList()));
    }

    public JobDto findByJobId(Long jobId){
        return jobMapper.toDto(jobRepository.findByJobId(jobId)
            .orElseThrow(() -> new EntityNotFoundException("Job not found with jobId : " + jobId)));
    }

}
