package com.flow.main.dto.controller.auth.file.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUploadRequestDto {

    private MultipartFile file;
    private String email;

}
