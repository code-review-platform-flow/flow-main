package com.flow.main.dto.jpa.job;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {

    private Long jobId;
    private String title;
    private String subtitle;
    private String description;
    private String imageUrl;
    private int version;

}
