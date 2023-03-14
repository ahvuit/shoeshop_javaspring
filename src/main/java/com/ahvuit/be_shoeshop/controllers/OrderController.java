package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Order;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.OrderOrderDetails;
import com.ahvuit.be_shoeshop.service.OrderService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/getAllOrders")
    ResponseEntity<ApiResult> getAllOrders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        return orderService.getAllOrders();
    }

    @GetMapping("/api/getOrderDetails/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return orderService.findById(id);
    }

    @GetMapping("/api/getOrderByUserId/{id}")
    ResponseEntity<ApiResult> findByUserId(@PathVariable String id) {
        return orderService.findByUserId(id);
    }

    @PostMapping("/api/insertOrder")
    ResponseEntity<ApiResult> insertOrder(@RequestBody OrderOrderDetails orders) {
        return orderService.insertOrder(orders);
    }

    @PutMapping("/api/paymentOrder/{id}")
    public ResponseEntity<ApiResult> paymentOrder(@RequestBody Order order, @PathVariable String id) {
        return orderService.paymentOrder(order, id);
    }

    @PutMapping("/api/cancelOrder/{id}")
    public ResponseEntity<ApiResult> cancelOrder(@PathVariable String id) {
        return orderService.cancelOrder(id);
    }

    @PutMapping("/api/updateOrder/{id}")
    public ResponseEntity<ApiResult> updateOrder(@RequestBody Order order, @PathVariable String id) {
        return orderService.updateOrder(order, id);
    }

}