package com.jtw.security_1.domain.product.controller;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.presentation.ProductDeleteRequest;
import com.jtw.security_1.domain.product.presentation.ProductJoinRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.product.service.CreateProductService;
import com.jtw.security_1.domain.product.service.DeleteProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepositories productRepositories;
    private final CreateProductService productService;
    private final DeleteProductService deleteProductService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> ProductList() {
        return ResponseEntity.ok().body(productRepositories.findAll());
    }

    @PostMapping("/join")
    public ResponseEntity<HttpStatus> ProductJoin(@RequestBody ProductJoinRequest joinRequest) {
        productService.productJoin(joinRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> ProductDelete(@RequestBody ProductDeleteRequest deleteRequest) {
        deleteProductService.DeleteProduct(deleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
