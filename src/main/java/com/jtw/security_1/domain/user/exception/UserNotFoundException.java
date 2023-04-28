package com.jtw.security_1.domain.user.exception;

import com.jtw.security_1.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(String.valueOf(ErrorCode.USERNAME_NOT_FOUND));
    }
}
