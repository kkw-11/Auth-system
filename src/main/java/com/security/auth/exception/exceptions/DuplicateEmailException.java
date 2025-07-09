package com.security.auth.exception.exceptions;


import com.security.auth.dto.common.ResponseCode;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super(ResponseCode.DUPLICATE_EMAIL.getMessage());
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
