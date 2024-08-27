package com.flow.main.controller;

import com.flow.main.dto.controller.job.get.all.JobGetAllResponseDto;
import com.flow.main.dto.controller.job.get.info.JobGetInfoResponseDto;
import com.flow.main.service.job.JobGetAllService;
import com.flow.main.service.job.JobGetInfoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobGetAllService jobGetAllService;
    private final JobGetInfoService jobGetInfoService;

    @GetMapping("/all")
    public ResponseEntity<JobGetAllResponseDto> getAll(){
        return ResponseEntity.ok(jobGetAllService.getAll());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobGetInfoResponseDto> gwtInfo(@PathVariable("jobId") Long jobId){
        return ResponseEntity.ok(jobGetInfoService.getInfo(jobId));
    }

}
