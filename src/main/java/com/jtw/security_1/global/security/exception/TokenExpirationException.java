package com.jtw.security_1.global.security.exception;

import lombok.Getter;
import com.jtw.security_1.global.exception.ErrorCode;

@Getter
public class TokenExpirationException extends RuntimeException {

    public TokenExpirationException() {
        super(String.valueOf(ErrorCode.TOKEN_IS_EXPIRED));
    }
}