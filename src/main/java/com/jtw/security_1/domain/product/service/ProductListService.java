package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductListService {

    private final ProductRepositories productRepositories;

    public List<Map<String, Object>> ProductList() {
        List<Product> productList = productRepositories.findAll();

        return productList.stream()
                .map(product -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", product.getProductName());
                    map.put("price", product.getPrice());
                    return map;
                })
                .toList();
    }
}
