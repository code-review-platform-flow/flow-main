package com.flow.main.dto.jpa.categories;

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
public class CategoriesDto {
    private Long categoryId;
    private String categoryName;
    private int version;
}
