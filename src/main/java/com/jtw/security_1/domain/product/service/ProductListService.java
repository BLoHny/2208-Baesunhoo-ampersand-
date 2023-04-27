package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductListService {

    private final ProductRepositories productRepositories;

    public void ProductList() {

    }
}
