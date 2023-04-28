package com.jtw.security_1.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jtw.security_1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
