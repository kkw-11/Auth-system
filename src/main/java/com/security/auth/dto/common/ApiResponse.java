package com.security.auth.dto.common;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private final boolean success;
    private final String code;
    private final String message;
    private final int status;
    private final LocalDateTime timestamp;
    private final T data;

    public static <T> ApiResponse<T> success(ResponseCode code, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .code(code.getCode())
                .message(code.getMessage())
                .status(code.getHttpStatus())
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(ResponseCode code) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code.getCode())
                .message(code.getMessage())
                .status(code.getHttpStatus())
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }
}
