package com.jtw.security_1.domain.product.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductJoinRequest {

    private String productname;
    private String content;
    private Long userid;
}
