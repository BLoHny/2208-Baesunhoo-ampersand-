package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.presentation.ProductDeleteRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteProductService {

    private final ProductRepositories productRepositories;
    private final UserRepository userRepository;
    private final UserUtil util;

    public void DeleteProduct(ProductDeleteRequest productDeleteRequest) {

        if (!productRepositories.existsByProductName(productDeleteRequest.getProductName())) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        User user = userRepository.findByPassword(util.currentUser().getPassword());
        Product product = productRepositories.findByProductName(productDeleteRequest.getProductName());

        if (Objects.equals(product.getUser().getId(), user.getId())) {
            productRepositories.deleteById(product.getProductId());
        } else {
            System.out.println("등록사용자와 ID가 일치 하지 않습니다.");
        }
    }
}
