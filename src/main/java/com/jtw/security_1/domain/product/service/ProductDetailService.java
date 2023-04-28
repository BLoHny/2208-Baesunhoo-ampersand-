package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductDetailService {

    private final ProductRepositories productRepositories;

    public Product productDetail(Long productId) {

        Product product = productRepositories.findByProductId(productId);

        if (!productRepositories.existsByProductName(product.getProductName())) {
            throw new RuntimeException("존재하지 않는 상품입니다");
        }
        return product;
    }
}
