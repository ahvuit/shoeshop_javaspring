package com.ahvuit.be_shoeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.Product;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;

@Service
public class ProductService {

        // CRUD product service

        @Autowired
        private ProductRepository repository;

        public ResponseEntity<ApiResult> getAllProducts() {
                try {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query product successfully", repository.findAll()));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        Optional<Product> foundProduct = repository.findById(id);
                        return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query product successfully", foundProduct))
                                        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                                        new ApiResult(false, 404, "Cannot find product", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> insertProduct(Product newProduct) {
                // 2 products must not have the same name !
                try {
                        List<Product> foundProducts = repository.findByName(newProduct.getName());
                        return foundProducts.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "insert new product successfully",
                                                        repository.save(newProduct)))
                                        : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                                                        new ApiResult(false, 404, "product name is already", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }

        }

        public ResponseEntity<ApiResult> updateProduct(Product newProduct, String id) {
                try {
                        Product updatedProduct = repository.findById(id)
                                        .map(product -> {
                                                product.setName(newProduct.getName());
                                                product.setDescription(newProduct.getDescription());
                                                product.setBrandId(newProduct.getBrandId());
                                                product.setCategoryId(newProduct.getCategoryId());
                                                product.setName(newProduct.getName());
                                                product.setPrice(newProduct.getPrice());
                                                product.setRate(newProduct.getPrice());
                                                product.setProductNew(newProduct.getProductNew());
                                                product.setName(newProduct.getName());
                                                product.setActive(newProduct.getActive());
                                                product.setImage(newProduct.getImage());
                                                product.setDateUpdated(newProduct.getDateUpdated());
                                                product.setUpdateBy(newProduct.getUpdateBy());
                                                return repository.save(product);
                                        }).orElseGet(() -> {
                                                newProduct.setProductId(id);
                                                return repository.save(newProduct);
                                        });
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Update Product successfully",
                                                        updatedProduct));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> deleteProduct(String id) {
                try {
                        boolean exists = repository.existsById(id);
                        if (exists) {
                                repository.deleteById(id);
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Delete product successfully ",
                                                                null));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find product to delete ",
                                                        null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

}
