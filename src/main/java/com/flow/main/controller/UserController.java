package com.flow.main.controller;

import com.flow.main.dto.controller.user.get.career.response.CareerGetResponseDto;
import com.flow.main.dto.controller.user.get.education.response.EducationGetResponseDto;
import com.flow.main.dto.controller.user.get.host.response.HostUserGetInfoResponseDto;
import com.flow.main.dto.controller.user.get.summary.request.UserSummaryRequestDto;
import com.flow.main.dto.controller.user.get.summary.response.UserSummaryResponseDto;
import com.flow.main.dto.controller.user.update.career.request.CareerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.career.response.CareerUpdateResponseDto;
import com.flow.main.dto.controller.user.update.education.request.EducationUpdateRequestDto;
import com.flow.main.dto.controller.user.update.education.response.EducationUpdateResponseDto;
import com.flow.main.dto.controller.user.update.oneliner.request.OneLinerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.oneliner.response.OneLinerUpdateResponseDto;
import com.flow.main.dto.controller.user.withdrawal.request.UserWithdrawalRequestDto;
import com.flow.main.dto.controller.user.withdrawal.response.UserWithdrawalResponseDto;
import com.flow.main.service.career.CareerGetService;
import com.flow.main.service.career.CareerUpdateService;
import com.flow.main.service.education.EducationGetService;
import com.flow.main.service.education.EducationUpdateService;
import com.flow.main.service.userinfo.UserInfoGetHostService;
import com.flow.main.service.userinfo.UserInfoOneLinerUpdateService;
import com.flow.main.service.userinfo.UserInfoSummaryService;
import com.flow.main.service.users.UsersWithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserInfoGetHostService userInfoGetHostService;
    private final UserInfoSummaryService userInfoSummaryService;
    private final EducationGetService educationGetService;
    private final CareerGetService careerGetService;
    private final UserInfoOneLinerUpdateService userInfoOneLinerUpdateService;
    private final EducationUpdateService educationUpdateService;
    private final CareerUpdateService careerUpdateService;
    private final UsersWithdrawalService usersWithdrawalService;

    @GetMapping("")
    public ResponseEntity<HostUserGetInfoResponseDto> getHostUserInfo(
            @RequestParam("hostEmail") String hostEmail,
            @RequestParam(name = "visitorEmail", required = false) String visitorEmail
    ) {
        return ResponseEntity.ok(userInfoGetHostService.getHostUserInfo(hostEmail, visitorEmail));
    }

    @GetMapping("/summary")
    public ResponseEntity<UserSummaryResponseDto> getUserSummary(
            @RequestParam("email") String email
    ) {
        return ResponseEntity.ok(userInfoSummaryService.getUserSummary(email));
    }

    @GetMapping("/education/{educationId}")
    public ResponseEntity<EducationGetResponseDto> getEducation(
            @PathVariable("educationId") Long educationId
    ) {
        return ResponseEntity.ok(educationGetService.getEducation(educationId));
    }

    @GetMapping("/career/{careerId}")
    public ResponseEntity<CareerGetResponseDto> getCareer(
            @PathVariable("careerId") Long careerId
    ) {
        return ResponseEntity.ok(careerGetService.getCareer(careerId));
    }

    @PatchMapping("/one-liner")
    public ResponseEntity<OneLinerUpdateResponseDto> updateOneLiner(
            @RequestBody final OneLinerUpdateRequestDto oneLinerUpdateRequestDto
    ) {
        return ResponseEntity.ok(userInfoOneLinerUpdateService.updateOneLiner(oneLinerUpdateRequestDto));
    }

    @PostMapping("/education")
    public ResponseEntity<EducationUpdateResponseDto> updateEducation(
            @RequestBody final EducationUpdateRequestDto educationUpdateRequestDto
    ) {
        return ResponseEntity.ok(educationUpdateService.updateEducation(educationUpdateRequestDto));
    }

    @PostMapping("/career")
    public ResponseEntity<CareerUpdateResponseDto> updateCareer(
            @RequestBody final CareerUpdateRequestDto careerUpdateRequestDto
    ) {
        return ResponseEntity.ok(careerUpdateService.updateCareer(careerUpdateRequestDto));
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<UserWithdrawalResponseDto> withdrawal(
            @RequestBody final UserWithdrawalRequestDto userWithdrawalRequestDto
    ) {
        return ResponseEntity.ok(usersWithdrawalService.withdrawal(userWithdrawalRequestDto));
    }
}
