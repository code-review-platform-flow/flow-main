package com.flow.main.controller;

import com.flow.main.dto.controller.file.request.FileUploadRequestDto;
import com.flow.main.dto.controller.file.response.FileUploadResponseDto;
import com.flow.main.service.file.FileForwardService;
import com.flow.main.service.userinfo.UserInfoFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final UserInfoFileUploadService userInfoFileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDto> upload(
        @RequestPart("file") MultipartFile file,
        @RequestPart("email") String email){

        log.info("file.getOriginalFileName : {}", file.getOriginalFilename());
        log.info("email : {}", email);

        return ResponseEntity.ok(userInfoFileUploadService.fileUpload(file, email));
    }

}
