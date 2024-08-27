package com.flow.main.repository;

import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.entity.JobEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query("SELECT j FROM JobEntity j")
    Optional<List<JobEntity>> findAllJobs();

    @Query("SELECT j FROM JobEntity j WHERE j.jobId = :jobId")
    Optional<JobEntity> findByJobId(@Param("jobId") Long jobId);

}
