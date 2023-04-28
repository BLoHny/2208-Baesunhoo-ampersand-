package com.jtw.security_1.domain.order.entity;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OrderCart")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")

    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
