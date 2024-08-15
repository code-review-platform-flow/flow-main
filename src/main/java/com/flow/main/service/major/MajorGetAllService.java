package com.flow.main.service.major;

import com.flow.main.dto.controller.auth.major.MajorGetAllDto;
import com.flow.main.dto.controller.auth.major.response.MajorGetAllResponseDto;
import com.flow.main.dto.jpa.major.MajorDto;
import com.flow.main.mapper.MajorMapper;
import com.flow.main.service.major.persistence.MajorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MajorGetAllService {

    private final MajorService majorService;
    private final MajorMapper majorMapper;

    public MajorGetAllResponseDto getAllMajor(){
        List<MajorDto> majorDtos = majorService.getAllMajor();
        List<MajorGetAllDto> majorGetAllDtoList = majorMapper.toMajorGetAllDtoList(majorDtos);
        return MajorGetAllResponseDto.builder()
            .majorGetAllDtoList(majorGetAllDtoList)
            .build();
    }

}
