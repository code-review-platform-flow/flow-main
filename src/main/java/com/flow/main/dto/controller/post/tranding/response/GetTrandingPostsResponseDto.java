package com.flow.main.dto.controller.post.tranding.response;

import com.flow.main.dto.controller.post.tranding.TrandingPostDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTrandingPostsResponseDto {

    List<TrandingPostDto> trandingPostsList;

}
