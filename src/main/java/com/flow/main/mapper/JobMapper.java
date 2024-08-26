package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.entity.JobEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper extends GenericMapper<JobDto, JobEntity> {
}
