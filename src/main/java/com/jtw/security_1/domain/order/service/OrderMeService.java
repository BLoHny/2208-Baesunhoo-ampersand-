package com.jtw.security_1.domain.order.service;

import com.jtw.security_1.domain.order.entity.Order;
import com.jtw.security_1.domain.order.repository.OrderRepository;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderMeService {

    private OrderRepository orderRepository;
    private final ProductRepositories productRepositories;
    private final UserRepository userRepository;
    private final UserUtil util;

    public List<Order> getMeOrderList() {
        User user = userRepository.findByPassword(util.currentUser().getPassword());

        return orderRepository.findByUserId(user.getId());
    }
}
