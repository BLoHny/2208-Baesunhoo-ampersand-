package com.jtw.security_1.domain.product.repositories;

import com.jtw.security_1.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {

    Boolean existsByProductname(String productname);

    boolean findContnetByProductid(Long productid);
}
