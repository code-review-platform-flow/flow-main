package com.flow.main.service.job.persistence;

import com.flow.main.mapper.JobMapper;
import com.flow.main.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

}
