package com.flow.main.common.aop;

import com.flow.main.common.dto.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RestVersionAdvice<T> implements ResponseBodyAdvice<T> {
    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public T beforeBodyWrite(
            T body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (body instanceof ApiResponse<?>) {  // 응답이 ApiResponse 인 경우만 처리
            ApiResponse<?> responseBody = (ApiResponse<?>) body;
            String apiVersion = extractApiVersion(request.getURI().getPath());
            responseBody.reflectVersion(apiVersion);
        }
        return body;
    }

    // 요청 URI에서 API 버전 추출하는 메서드
    private String extractApiVersion(String path) {
        String[] segments = path.split("/");
        for (String segment : segments) {
            if (!segment.isEmpty()) {
                return segment; // 첫 번째 비어 있지 않은 문자열을 API 버전으로 간주
            }
        }
        return "unknown";
    }
}
