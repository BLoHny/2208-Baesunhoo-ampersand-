package com.jtw.security_1.domain.order.service;

import com.jtw.security_1.domain.order.entity.Order;
import com.jtw.security_1.domain.order.exception.ProductNotFoundException;
import com.jtw.security_1.domain.order.repository.OrderRepository;
import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderInsertService {

    private OrderRepository orderRepository;
    private final ProductRepositories productRepositories;
    private final UserRepository userRepository;
    private final UserUtil util;

    public void InsertOrder(Long productId) {

        User user = userRepository.findByPassword(util.currentUser().getPassword());
        Product product = productRepositories.findByProductId(productId);

        if (!productRepositories.existsById(productId)) {
            throw new ProductNotFoundException();
        }

        Order order = Order.builder()
                .product(product)
                .user(user)
                .build();

        orderRepository.save(order);
    }
}
