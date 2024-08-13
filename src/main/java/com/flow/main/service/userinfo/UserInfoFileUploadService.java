package com.flow.main.service.userinfo;

import com.flow.main.dto.controller.file.request.FileUploadRequestDto;
import com.flow.main.dto.controller.file.response.FileUploadResponseDto;
import com.flow.main.dto.jpa.userinfo.UserInfoDto;
import com.flow.main.dto.jpa.users.UsersDto;
import com.flow.main.service.file.FileForwardService;
import com.flow.main.service.userinfo.persistence.UserInfoService;
import com.flow.main.service.users.persistence.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserInfoFileUploadService {

    private final UsersService usersService;
    private final UserInfoService userInfoService;
    private final FileForwardService fileForwardService;

    public FileUploadResponseDto fileUpload(FileUploadRequestDto fileUploadRequestDto) {

        UsersDto usersDto = usersService.findByEmail(fileUploadRequestDto.getEmail());
        UserInfoDto userInfoDto = userInfoService.findByUserId(usersDto.getUserId());

        FileUploadResponseDto fileUploadResponseDto = fileForwardService.fileForward(fileUploadRequestDto.getMultipartFile());
        userInfoDto.setProfileUrl(fileUploadResponseDto.getFilePath());
        userInfoService.save(userInfoDto);

        return fileUploadResponseDto;
    }
}
