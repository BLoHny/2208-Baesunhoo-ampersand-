package com.jtw.security_1.domain.product.controller;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.presentation.ProductJoinRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.product.service.ProductService;
import com.jtw.security_1.domain.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepositories productRepositories;
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> ProductList() {
        return ResponseEntity.ok().body(productRepositories.findAll());
    }

    @PostMapping("/join")
    public ResponseEntity<String> ProductJoin(@RequestBody ProductJoinRequest joinRequest, @AuthenticationPrincipal User user) {
        String success = productService.productJoin(joinRequest, user);

        return ResponseEntity.ok().body(success);
    }

    @GetMapping("/detail")
    public ResponseEntity<String> ProductDetail(@RequestParam(value = "productid") Long productid) {
        String detail = productService.findProductDetail(productid);

        return ResponseEntity.ok().body(detail);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> ProductDelete(@RequestParam(value = "productid") Long productid, @AuthenticationPrincipal User user) {
        String delete = productService.ProductDelete(productid, user);

        return ResponseEntity.ok().body(delete);
    }
}
