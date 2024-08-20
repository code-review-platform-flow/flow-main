package com.flow.main.service.career;

import com.flow.main.dto.controller.user.update.career.request.CareerUpdateRequestDto;
import com.flow.main.dto.controller.user.update.career.response.CareerUpdateResponseDto;
import com.flow.main.dto.jpa.career.CareerDto;
import com.flow.main.dto.jpa.education.EducationDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.career.persistence.CareerService;
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
public class CareerUpdateService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final CareerService careerService;

    public CareerUpdateResponseDto updateCareer(CareerUpdateRequestDto careerUpdateRequestDto){

        Long careerId = careerUpdateRequestDto.getCareerId();
        String description = careerUpdateRequestDto.getDescription();
        String title = careerUpdateRequestDto.getTitle();
        LocalDate startDate = Optional.ofNullable(careerUpdateRequestDto.getStartDate())
            .map(date -> date.atMonth(12).atEndOfMonth())
            .orElse(null);
        LocalDate endDate = Optional.ofNullable(careerUpdateRequestDto.getEndDate())
            .map(date -> date.atMonth(12).atEndOfMonth())
            .orElse(null);

        UsersDto usersDto = usersService.findByEmail(careerUpdateRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        if(careerId == null && description.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        CareerDto careerDto = Optional.ofNullable(careerId)
            .map(careerService::findByCareerId)
            .orElse(new CareerDto());
        careerDto.setUserInfoId(userInfoDto.getUserInfoId());
        careerDto.setTitle(title);
        careerDto.setStartDate(startDate);
        careerDto.setEndDate(endDate);
        if(description.isEmpty()){
            careerService.delete(careerDto);
            return null;
        }
        careerDto.setDescription(description);
        CareerDto savedCareerDto = careerService.save(careerDto);

        return CareerUpdateResponseDto.builder()
            .careerId(savedCareerDto.getCareerId())
            .build();
    }
}
