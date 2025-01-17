package com.flow.main.dto.controller.post.keyword.response;

import com.flow.main.dto.controller.post.keyword.FindByKeywordDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class FindByKeywordResponseDto {

    List<FindByKeywordDto> findByKeywordDtoList;

}
