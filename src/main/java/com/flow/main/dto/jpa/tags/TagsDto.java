package com.flow.main.dto.jpa.tags;

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
public class TagsDto {

    private Long tagId;
    private String tagName;
    private int version;

}
