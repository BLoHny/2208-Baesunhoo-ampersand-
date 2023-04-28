package com.jtw.security_1.domain.product.exception;

import com.jtw.security_1.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ProductDuplicatedException extends RuntimeException{

    public ProductDuplicatedException() {
        super(String.valueOf(ErrorCode.PRODUCT_DUPLICATED));
    }
}
