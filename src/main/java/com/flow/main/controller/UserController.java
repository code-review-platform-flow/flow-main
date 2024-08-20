package com.flow.main.controller;

import com.flow.main.dto.controller.user.get.career.response.CareerGetResponseDto;
import com.flow.main.dto.controller.user.get.education.response.EducationGetResponseDto;
import com.flow.main.dto.controller.user.get.host.request.HostUserGetInfoRequestDto;
import com.flow.main.dto.controller.user.get.host.response.HostUserGetInfoResponseDto;
import com.flow.main.dto.controller.user.update.career.request.CareerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.career.response.CareerUpdateResponseDto;
import com.flow.main.dto.controller.user.update.education.request.EducationUpdateRequestDto;
import com.flow.main.dto.controller.user.update.education.response.EducationUpdateResponseDto;
import com.flow.main.dto.controller.user.update.oneliner.request.OneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.OneLinerUpdateResponseDto;
import com.flow.main.service.career.CareerGetService;
import com.flow.main.service.career.CareerUpdateService;
import com.flow.main.service.career.persistence.CareerService;
import com.flow.main.service.education.EducationGetService;
import com.flow.main.service.education.EducationUpdateService;
import com.flow.main.service.user.HostUserGetInfoService;
import com.flow.main.service.userinfo.UserInfoOneLinerUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final HostUserGetInfoService hostUserGetInfoService;
    private final EducationGetService educationGetService;
    private final CareerGetService careerGetService;
    private final UserInfoOneLinerUpdateService userInfoOneLinerUpdateService;
    private final EducationUpdateService educationUpdateService;
    private final CareerUpdateService careerUpdateService;

    @GetMapping("/{hostEmail}")
    public ResponseEntity<HostUserGetInfoResponseDto> getHostUserInfo(@PathVariable("hostEmail") String hostEmail, @RequestBody(required = false) HostUserGetInfoRequestDto hostUserGetInfoRequestDto){
        log.info("hostEmail : {}", hostEmail);
        log.info("visitorEmail : {}", hostUserGetInfoRequestDto.getVisitorEmail());

        return ResponseEntity.ok(hostUserGetInfoService.getHostUserInfo(hostEmail, hostUserGetInfoRequestDto));
    }

    @GetMapping("/education/{educationId}")
    public ResponseEntity<EducationGetResponseDto> getEducation(@PathVariable("educationId") Long educationId){
        log.info("educationId : {}", educationId);

        return ResponseEntity.ok(educationGetService.getEducation(educationId));
    }

    @GetMapping("/career/{careerId}")
    public ResponseEntity<CareerGetResponseDto> getCareer(@PathVariable("careerId") Long careerId){
        log.info("educationId : {}", careerId);

        return ResponseEntity.ok(careerGetService.getCareer(careerId));
    }

    @PatchMapping("/one-liner")
    public ResponseEntity<OneLinerUpdateResponseDto> updateOneLiner(@RequestBody final OneLinerUpdateRequestDto oneLinerUpdateRequestDto){
        log.info("email : {}", oneLinerUpdateRequestDto.getEmail());
        log.info("oneLiner : {}", oneLinerUpdateRequestDto.getOneLiner());

        return ResponseEntity.ok(userInfoOneLinerUpdateService.updateOneLiner(oneLinerUpdateRequestDto));
    }

    @PostMapping("/education")
    public ResponseEntity<EducationUpdateResponseDto> updateEducation(@RequestBody final EducationUpdateRequestDto educationUpdateRequestDto){
        log.info("email : {}", educationUpdateRequestDto.getEmail());
        log.info("educationId : {}", educationUpdateRequestDto.getEducationId());
        log.info("schoolName : {}", educationUpdateRequestDto.getSchoolName());
        log.info("startDate : {}", educationUpdateRequestDto.getStartDate());
        log.info("endDate : {}", educationUpdateRequestDto.getEndDate());

        return ResponseEntity.ok(educationUpdateService.updateEducation(educationUpdateRequestDto));
    }

    @PostMapping("/career")
    public ResponseEntity<CareerUpdateResponseDto> updateCareer(@RequestBody final
        CareerUpdateRequestDto careerUpdateRequestDto){
        log.info("email : {}", careerUpdateRequestDto.getEmail());
        log.info("careerId : {}", careerUpdateRequestDto.getCareerId());
        log.info("title : {}", careerUpdateRequestDto.getTitle());
        log.info("description : {}", careerUpdateRequestDto.getDescription());
        log.info("startDate : {}", careerUpdateRequestDto.getStartDate());
        log.info("endDate : {}", careerUpdateRequestDto.getEndDate());

        return ResponseEntity.ok(careerUpdateService.updateCareer(careerUpdateRequestDto));
    }
}
