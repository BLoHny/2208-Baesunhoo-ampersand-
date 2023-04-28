package com.jtw.security_1.domain.order.exception;

import lombok.Getter;
import com.jtw.security_1.global.exception.ErrorCode;

@Getter
public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() {
        super(String.valueOf(ErrorCode.PRODUCT_NOTFOUND));
    }
}
