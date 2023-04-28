package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.exception.ProductDuplicatedException;
import com.jtw.security_1.domain.product.presentation.ProductJoinRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductService {

    private final ProductRepositories productRepositories;
    private final UserRepository userRepository;
    private final UserUtil util;

    public void productJoin(ProductJoinRequest joinRequest) {

        if(productRepositories.existsByProductName(joinRequest.getProductName())) {
            throw new ProductDuplicatedException();
        }


        User user = userRepository.findByPassword(util.currentUser().getPassword());

        System.out.println(user.getUserName());
        Product product = Product.builder()
                .productName(joinRequest.getProductName())
                .content(joinRequest.getContent())
                .price(joinRequest.getPrice())
                .user(user)
                .build();

        productRepositories.save(product);
    }
}
