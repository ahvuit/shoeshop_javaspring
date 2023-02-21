package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Product;
import com.ahvuit.be_shoeshop.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/getAllProducts")
    ResponseEntity<ApiResult> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/getProductById/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping("/api/insertProduct")
    ResponseEntity<ApiResult> insertProduct(@RequestBody Product product) {
        // 2 products must not have the same name !
        return productService.insertProduct(product);

    }

    @PutMapping("/api/updateProduct/{id}")
    public ResponseEntity<ApiResult> updateProduct(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/api/deleteProduct/{id}")
    public ResponseEntity<ApiResult> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
