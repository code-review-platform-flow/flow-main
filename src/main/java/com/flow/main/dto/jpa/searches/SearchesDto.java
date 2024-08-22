package com.flow.main.dto.jpa.searches;

import java.time.LocalDateTime;
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
public class SearchesDto {
    private Long searchId;
    private Long userId;
    private String searchQuery;
    private LocalDateTime searchDate;
    private int version;
}
