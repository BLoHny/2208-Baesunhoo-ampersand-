package com.jtw.security_1.domain.product.entity;

import com.jtw.security_1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid", nullable = false)
    private Long productid;

    @Column(name = "productname")
    private String productname;

    @Column(name = "content")
    private String content;

    private Long userid;
}
