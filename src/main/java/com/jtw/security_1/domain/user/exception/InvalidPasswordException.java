package com.jtw.security_1.domain.user.exception;

import com.jtw.security_1.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super(String.valueOf(ErrorCode.INVALID_PASSWORD));
    }
}
