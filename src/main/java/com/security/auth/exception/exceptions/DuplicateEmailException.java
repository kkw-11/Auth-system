package com.security.auth.exception.exceptions;

import com.security.auth.exception.ErrorCode;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL.getMessage());
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
