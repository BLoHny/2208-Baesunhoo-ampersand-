package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.order.exception.ProductNotFoundException;
import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.exception.ProductInvalidUserIdException;
import com.jtw.security_1.domain.product.dto.ProductDeleteRequest;
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
            throw new ProductNotFoundException();
        }

        User user = userRepository.findByPassword(util.currentUser().getPassword());
        Product product = productRepositories.findByProductName(productDeleteRequest.getProductName());

        if (Objects.equals(product.getUser().getId(), user.getId())) {
            productRepositories.deleteById(product.getProductId());
        } else {
            throw new ProductInvalidUserIdException();
        }
    }
}
