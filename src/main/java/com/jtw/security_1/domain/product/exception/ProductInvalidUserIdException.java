package com.jtw.security_1.domain.product.exception;

import com.jtw.security_1.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ProductInvalidUserIdException extends RuntimeException{

    public ProductInvalidUserIdException() {
        super(String.valueOf(ErrorCode.PRODUCT_INVALID_USERID));
    }
}
