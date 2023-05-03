package com.jtw.security_1.domain.product.controller;

import com.jtw.security_1.domain.product.entity.Product;
import com.jtw.security_1.domain.product.dto.ProductDeleteRequest;
import com.jtw.security_1.domain.product.dto.ProductJoinRequest;
import com.jtw.security_1.domain.product.repositories.ProductRepositories;
import com.jtw.security_1.domain.product.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepositories productRepositories;
    private final CreateProductService productService;
    private final DeleteProductService deleteProductService;
    private final ProductMeListService productMeListService;
    private final ProductDetailService productDetailService;
    private final ProductListService productListService;

    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> ProductList() {
        List<Map<String, Object>> productList = productListService.ProductList();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> ProductJoin(@RequestBody ProductJoinRequest joinRequest) {
        productService.productJoin(joinRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> ProductDelete(@RequestBody ProductDeleteRequest deleteRequest) {
        deleteProductService.DeleteProduct(deleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me/list")
    public List<Product> productMeList() {
        List<Product> meList = productMeListService.ProductMeList();

        return new ResponseEntity<>(meList, HttpStatus.OK).getBody();
    }

    @GetMapping("/detail")
    public Product productDetail(@RequestParam Long productId) {
        Product product =  productDetailService.productDetail(productId);
        return new ResponseEntity<>(product, HttpStatus.OK).getBody();
    }
}
