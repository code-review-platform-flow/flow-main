package com.flow.main.controller;

import com.flow.main.dto.controller.user.get.request.UserGetInfoRequestDto;
import com.flow.main.dto.controller.user.update.career.request.CareerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.career.response.CareerUpdateResponseDto;
import com.flow.main.dto.controller.user.update.education.request.EducationUpdateRequestDto;
import com.flow.main.dto.controller.user.update.education.response.EducationUpdateResponseDto;
import com.flow.main.dto.controller.user.update.oneliner.request.OneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.OneLinerUpdateResponseDto;
import com.flow.main.service.career.CareerUpdateService;
import com.flow.main.service.education.EducationUpdateService;
import com.flow.main.service.userinfo.UserInfoOneLinerUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    private final UserInfoOneLinerUpdateService userInfoOneLinerUpdateService;
    private final EducationUpdateService educationUpdateService;
    private final CareerUpdateService careerUpdateService;

    @GetMapping("/{userInfoEmail}")
    public void getUserInfo(@PathVariable("userInfoEmail") String userInfoEmail, @RequestBody(required = false) UserGetInfoRequestDto userGetInfoRequestDto){
        log.info("userInfoEmail : {}", userInfoEmail);
        log.info("email : {}", userGetInfoRequestDto.getEmail());
    }

    @PatchMapping("/one-liner")
    public ResponseEntity<OneLinerUpdateResponseDto> updateOneLiner(@RequestBody final OneLinerUpdateRequestDto oneLinerUpdateRequestDto){
        log.info("email : {}", oneLinerUpdateRequestDto.getEmail());
        log.info("oneLiner : {}", oneLinerUpdateRequestDto.getOneLiner());

        return ResponseEntity.ok(userInfoOneLinerUpdateService.updateOneLiner(
            oneLinerUpdateRequestDto));
    }

    @PostMapping("/education")
    public ResponseEntity<EducationUpdateResponseDto> updateEducation(@RequestBody final EducationUpdateRequestDto educationUpdateRequestDto){
        log.info("email : {}", educationUpdateRequestDto.getEmail());
        log.info("educationId : {}", educationUpdateRequestDto.getEducationId());
        log.info("schoolNAme : {}", educationUpdateRequestDto.getSchoolName());
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
