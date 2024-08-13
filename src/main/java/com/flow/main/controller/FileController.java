package com.flow.main.controller;

import com.flow.main.dto.controller.file.request.FileUploadRequestDto;
import com.flow.main.dto.controller.file.response.FileUploadResponseDto;
import com.flow.main.service.file.FileForwardService;
import com.flow.main.service.userinfo.UserInfoFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final UserInfoFileUploadService userInfoFileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponseDto> upload(@RequestBody final FileUploadRequestDto fileUploadRequestDto){
        return ResponseEntity.ok(userInfoFileUploadService.fileUpload(fileUploadRequestDto));
    }

}
