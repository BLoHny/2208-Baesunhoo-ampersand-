package com.jtw.security_1.domain.product.service;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.user.entity.User;
import com.jtw.security_1.domain.user.repository.UserRepository;
import com.jtw.security_1.global.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductMeListService {

    private final ProductRepositories productRepositories;
    private final UserRepository userRepository;
    private final UserUtil util;

    public List<Product> ProductMeList() {

        User user = userRepository.findByPassword(util.currentUser().getPassword());

        return productRepositories.findByUserId(user.getId());
    }
}
