package com.security.auth.dto.common;

import org.springframework.http.HttpStatus;

public enum ResponseCode {
    //성공 응답 코드
    SIGNUP_SUCCESS(HttpStatus.OK, "회원가입 성공"),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),


    //실패 응답코드
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일입니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseCode(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus.value();
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return this.name();
    }
}
