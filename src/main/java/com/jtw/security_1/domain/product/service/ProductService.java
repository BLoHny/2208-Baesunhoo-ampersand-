package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.presentation.ProductJoinRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepositories productRepositories;

    public String productJoin(ProductJoinRequest joinRequest, User user) {

        if(productRepositories.existsByProductname(joinRequest.getProductname())) {
            throw new RuntimeException("이미 있는 상품입니다.");
        }

        Product product = Product.builder()
                .productname(joinRequest.getProductname())
                .content(joinRequest.getContent())
                .userid(user.getId())
                .build();

        productRepositories.save(product);

        return "TRUE";
    }

    public String findProductDetail(Long productid) {

        if(productRepositories.findContnetByProductid(productid))
        {
            throw new RuntimeException("없는 상품 아이디입니다.");
        }
        Optional<Product> product = productRepositories.findById(productid);

        return product.get().getContent();
    }

    public String ProductDelete(Long productid, User user) {
        Optional<Product> product = productRepositories.findById(productid);

        if(!Objects.equals(user.getId(), product.get().getUserid())) {
            throw new RuntimeException("상품을 등록한 유저가 아닙니다.");
        }

        productRepositories.deleteById(productid);

        return "success";
    }
}
