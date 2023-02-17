package com.ahvuit.be_shoeshop.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Product;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product")
    public ResponseEntity<ApiResult> getAllProducts() {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Query product successfully", this.productRepository.findAll())
        // you can replace "ok" with your defined "error code"
        );
    }

    @GetMapping("/product/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        Optional<Product> foundProduct = this.productRepository.findById(id);
        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ApiResult(true, 200, "Query product successfully", foundProduct)
        // you can replace "ok" with your defined "error code"
        )
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResult(false, 404, "Cannot find product with id = " + id, null));
    }
}
