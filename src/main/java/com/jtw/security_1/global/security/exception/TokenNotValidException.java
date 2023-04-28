package com.jtw.security_1.global.security.exception;

import lombok.Getter;
import com.jtw.security_1.global.exception.ErrorCode;

@Getter
public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException() {
        super(String.valueOf(ErrorCode.TOKEN_NOT_VALID));
    }
}
