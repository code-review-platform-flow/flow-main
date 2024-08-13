package com.flow.main.service.file;

import com.flow.main.common.property.FileProperty;
import com.flow.main.dto.controller.auth.file.response.FileUploadResponseDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileForwardService {

    private final FileProperty fileProperty;

    public FileUploadResponseDto fileForward(MultipartFile file) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();


            ByteArrayResource byteArrayResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename(); // 실제 파일 이름을 반환
                }
            };

            body.add("file", byteArrayResource);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            URI uri = UriComponentsBuilder
                .fromUriString(fileProperty.getUrl())
                .path(fileProperty.getPath())
                .encode().build().toUri();

            log.info("uri : {}", uri);
            log.info("headers : {}", headers.getContentType());

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FileUploadResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, FileUploadResponseDto.class);
            if (response.getStatusCode().is5xxServerError()){ throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); }

            return response.getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
