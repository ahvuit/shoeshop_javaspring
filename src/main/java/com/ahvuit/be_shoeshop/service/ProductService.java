package com.ahvuit.be_shoeshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SizeTable;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.models.ProductModel;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;
import com.ahvuit.be_shoeshop.repositories.SizeTableRepository;

@Service
public class ProductService {

        // CRUD product service

        @Autowired
        private ProductRepository repository;
        @Autowired
        private SizeTableRepository sizeTableRepository;

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
                        if (foundProducts.isEmpty()) {
                                newProduct.setStock(0);
                                newProduct.setActive(true);
                                newProduct.setPurchase(0);
                                newProduct.setProductNew(true);
                                newProduct.setCreatedDate(new Date());
                                newProduct.setDateUpdated(new Date());
                                repository.save(newProduct);

                                SizeTable sizeTable = new SizeTable();
                                sizeTable.setProductId(newProduct.getProductId());
                                sizeTable.setS38(0);
                                sizeTable.setS39(0);
                                sizeTable.setS40(0);
                                sizeTable.setS41(0);
                                sizeTable.setS42(0);
                                sizeTable.setS43(0);
                                sizeTable.setS44(0);
                                sizeTable.setS45(0);
                                sizeTable.setS46(0);
                                sizeTable.setS47(0);
                                sizeTable.setS48(0);
                                sizeTableRepository.save(sizeTable);

                                ProductModel productResponse = new ProductModel(newProduct.getProductId(),
                                                newProduct.getName(),
                                                newProduct.getDescription(), newProduct.getBrandId(),
                                                newProduct.getCategoryId(), newProduct.getPrice(),
                                                newProduct.getRate(), newProduct.getProductNew(),
                                                newProduct.getPurchase(), newProduct.getStock(), newProduct.getActive(),
                                                newProduct.getImage(),
                                                newProduct.getCreatedDate(), newProduct.getDateUpdated(),
                                                newProduct.getUpdateBy(), sizeTable);

                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "insert new product successfully",
                                                                productResponse));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
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
