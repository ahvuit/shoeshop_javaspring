package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.SalesModel;
import com.ahvuit.be_shoeshop.service.SaleDetailsService;

@RestController
public class SaleDetailsController {
    @Autowired
    private SaleDetailsService saleDetailsService;

    @GetMapping("/api/getSaleDetailsBySalesId/{id}")
    ResponseEntity<ApiResult> getSaleDetailsBySalesId(@PathVariable String id) {
        return saleDetailsService.findById(id);
    }

    @PostMapping("/api/insertSalesDetails")
    ResponseEntity<ApiResult> insertSalesDetails(@RequestBody SalesModel salesModel) {
        return saleDetailsService.insertSalesDetails(salesModel);
    }

    @DeleteMapping("/api/deleteSaleDetails/{id}")
    public ResponseEntity<ApiResult> deleteSaleDetails(@PathVariable String id) {
        return saleDetailsService.deleteSaleDetails(id);
    }
}
