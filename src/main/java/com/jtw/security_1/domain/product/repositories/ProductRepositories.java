package com.jtw.security_1.domain.product.repositories;

import com.jtw.security_1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    Boolean existsByProductName(String productName);

    Product findByProductName(String productName);

    Product findByProductId(Long productId);

    List<Product> findByUserId(Long userId);

}
