package com.flow.main.service.file;

import com.flow.main.common.property.FileProperty;
import com.flow.main.dto.controller.file.response.FileUploadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class FileForwardService {

    private final FileProperty fileProperty;

    public FileUploadResponseDto fileForward(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(fileProperty.getUrl())
                .path(fileProperty.getPath())
                .encode().build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FileUploadResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, FileUploadResponseDto.class);
        if (response.getStatusCode().is5xxServerError()){ throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); }
        return response.getBody();
    }
}
