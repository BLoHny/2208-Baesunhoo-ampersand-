package com.jtw.security_1.domain.order.controller;

import com.jtw.security_1.domain.order.entity.Order;
import com.jtw.security_1.domain.order.service.OrderInsertService;
import com.jtw.security_1.domain.order.service.OrderMeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderInsertService orderInsertService;
    private OrderMeService orderMeService;

    @PostMapping("/insert")
    public ResponseEntity<?> CreateOrder(@RequestParam Long productId) {
        orderInsertService.InsertOrder(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me/list")
    public List<Order> OrderMeList() {
        List<Order> orderList = orderMeService.getMeOrderList();
        return new ResponseEntity<>(orderList, HttpStatus.OK).getBody();
    }
}
