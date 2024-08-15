package com.flow.main.dto.controller.auth.major.response;

import com.flow.main.dto.controller.auth.major.MajorGetAllDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MajorGetAllResponseDto {

    List<MajorGetAllDto> majorGetAllDtoList;

}
