package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Sales;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.SalesService;

@RestController
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/api/getAllSales")
    ResponseEntity<ApiResult> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/api/getSalesById/{id}")
    ResponseEntity<ApiResult> getSalesById(@PathVariable String id) {
        return salesService.findById(id);
    }

    @GetMapping("/api/getAllSalesActive")
    ResponseEntity<ApiResult> getAllSalesActive() {
        return salesService.getAllSalesActive();
    }

    @GetMapping("/api/getAllSalesComingSoon")
    ResponseEntity<ApiResult> getAllSalesComingSoon() {
        return salesService.getAllSalesComingSoon();
    }

    @PostMapping("/api/insertSales")
    ResponseEntity<ApiResult> insertSales(@RequestBody Sales sales) {
        return salesService.insertSales(sales);
    }

    @PutMapping("/api/updateSales/{id}")
    ResponseEntity<ApiResult> updateSales(@RequestBody Sales sales, @PathVariable String id) {
        return salesService.updateSales(sales, id);
    }

}
