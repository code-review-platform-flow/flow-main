package com.flow.main.dto.controller.job.get.all;

import com.flow.main.dto.controller.job.get.JobIdDto;
import java.util.List;
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
public class JobGetAllResponseDto {

    private List<JobIdDto> jobIdList;

}
