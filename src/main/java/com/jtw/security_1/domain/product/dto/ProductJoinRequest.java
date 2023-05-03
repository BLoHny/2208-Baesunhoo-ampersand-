package com.jtw.security_1.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductJoinRequest {

    private String productName;
    private String content;
    private int price;
}
