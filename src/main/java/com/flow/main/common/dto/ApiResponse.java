package com.flow.main.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiResponse<T> {
    private final int status;   // HTTP 상태 코드
    private String message;     // 응답 메시지
    private String version;
    private final T data;       // 응답 데이터

    public void reflectVersion(String apiVersion) {
        version = apiVersion;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", null, data);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(HttpStatus.OK.value(), message, null, data);
    }

    public static ApiResponse<Void> error(String message, HttpStatus status) {
        return new ApiResponse<>(status.value(), message, null, null);
    }
}
