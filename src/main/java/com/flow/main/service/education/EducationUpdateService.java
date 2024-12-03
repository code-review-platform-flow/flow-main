package com.flow.main.service.education;

import com.flow.main.dto.controller.user.update.education.request.EducationUpdateRequestDto;
import com.flow.main.dto.controller.user.update.education.response.EducationUpdateResponseDto;
import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.education.persistence.EducationService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EducationUpdateService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final EducationService educationService;

    public EducationUpdateResponseDto updateEducation(EducationUpdateRequestDto educationUpdateRequestDto){

        Long educationId = educationUpdateRequestDto.getEducationId();
        String schoolName = educationUpdateRequestDto.getSchoolName();
        LocalDate startDate = Optional.ofNullable(educationUpdateRequestDto.getStartDate())
                .map(date -> date.atMonth(12).atEndOfMonth())
                .orElse(null);
        LocalDate endDate = Optional.ofNullable(educationUpdateRequestDto.getEndDate())
            .map(date -> date.atMonth(12).atEndOfMonth())
            .orElse(null);

        UsersDto usersDto = usersService.findByEmail(educationUpdateRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        if(educationId == null && schoolName.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        EducationDto educationDto = Optional.ofNullable(educationId)
            .map(educationService::findByEducationId)
            .orElse(new EducationDto());
        educationDto.setUserInfoId(userInfoDto.getUserInfoId());
        educationDto.setStartDate(startDate);
        educationDto.setEndDate(endDate);
        if (schoolName.isEmpty()){
            educationService.delete(educationDto);
            return null;
        }
        educationDto.setSchoolName(schoolName);
        EducationDto savedEducationDto = educationService.save(educationDto);

        return EducationUpdateResponseDto.builder()
            .educationId(savedEducationDto.getEducationId())
            .build();
    }
}