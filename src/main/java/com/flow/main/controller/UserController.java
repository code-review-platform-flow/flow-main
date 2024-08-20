package com.flow.main.controller;

import com.flow.main.dto.controller.user.get.request.UserGetInfoRequestDto;
import com.flow.main.dto.controller.user.update.education.request.EducationUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.request.OneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.OneLinerUpdateResponseDto;
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

    private final UserInfoOneLinerUpdateService userInfoOneLinerUpdateService;

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
    public void updateEducation(@RequestBody final EducationUpdateRequestDto educationUpdateRequestDto){
        log.info("email : {}", educationUpdateRequestDto.getEmail());
        log.info("schoolNAme : {}", educationUpdateRequestDto.getSchoolName());
        log.info("startDate : {}", educationUpdateRequestDto.getStartDate());
        log.info("endDate : {}", educationUpdateRequestDto.getEndDate());


    }
}
