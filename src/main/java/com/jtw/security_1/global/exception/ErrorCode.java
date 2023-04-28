package com.jtw.security_1.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    TOKEN_IS_EXPIRED(HttpStatus.UNAUTHORIZED, ""),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "" ),
    PRODUCT_NOTFOUND(HttpStatus.NOT_FOUND, ""),
    PRODUCT_DUPLICATED(HttpStatus.CONFLICT, ""),
    PRODUCT_INVALID_USERID(HttpStatus.UNAUTHORIZED, "");


    private final HttpStatus httpStatus;
    private final String message;

}
