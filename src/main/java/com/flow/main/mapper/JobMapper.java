package com.flow.main.mapper;

import com.flow.main.common.mapper.GenericMapper;
import com.flow.main.dto.jpa.job.JobDto;
import com.flow.main.entity.JobEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper extends GenericMapper<JobDto, JobEntity> {

    List<JobDto> toListDto(List<JobEntity> jobEntities);

    List<JobEntity> toListEntity(List<JobDto> jobDtos);

}
