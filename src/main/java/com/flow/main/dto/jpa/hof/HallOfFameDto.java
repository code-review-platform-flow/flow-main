package com.flow.main.dto.jpa.hof;

import java.time.LocalDate;
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
public class HallOfFameDto {

    private Long hofId;
    private Long userId;
    private int rank;
    private LocalDate dateAwarded;
    private int version;

}
