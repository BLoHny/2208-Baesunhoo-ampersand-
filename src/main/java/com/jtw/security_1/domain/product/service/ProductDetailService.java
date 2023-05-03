package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.order.exception.ProductNotFoundException;
import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDetailService {

    private final ProductRepositories productRepositories;

    public Product productDetail(Long productId) {

        Product product = productRepositories.findByProductId(productId);

        if (!productRepositories.existsByProductName(product.getProductName())) {
            throw new ProductNotFoundException();
        }
        return product;
    }
}
