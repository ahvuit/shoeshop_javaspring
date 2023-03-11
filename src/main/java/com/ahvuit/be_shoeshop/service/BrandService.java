package com.ahvuit.be_shoeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Brand;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.BrandRepository;

@Service
public class BrandService {

        // CRUD brand service

        @Autowired
        private BrandRepository repository;

        public ResponseEntity<ApiResult> getAllBrands() {
                try {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query brand successfully", repository.findAll()));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> findById(String id) {
                try {
                        Optional<Brand> foundBrand = repository.findById(id);
                        return foundBrand.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Query brand successfully", foundBrand))
                                        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                                        new ApiResult(false, 404, "Cannot find brand", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> insertBrand(Brand newBrand) {
                try {
                        List<Brand> foundProducts = repository.findByBrandName(newBrand.getBrandName());
                        return foundProducts.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "insert new brand successfully",
                                                        repository.save(newBrand)))
                                        : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                                                        new ApiResult(false, 404, "Brand name is already", null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> updateBrand(Brand newBrand, String id) {
                try {
                        Brand updatedBrand = repository.findById(id)
                                        .map(brand -> {
                                                brand.setBrandName(newBrand.getBrandName());
                                                brand.setInformation(newBrand.getInformation());
                                                brand.setLogo(newBrand.getLogo());
                                                return repository.save(brand);
                                        }).orElseGet(() -> {
                                                newBrand.setBrandId(id);
                                                return repository.save(newBrand);
                                        });
                        return ResponseEntity.status(HttpStatus.OK).body(
                                        new ApiResult(true, 200, "Update brand successfully",
                                                        updatedBrand));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

        public ResponseEntity<ApiResult> deleteBrand(String id) {
                try {
                        boolean exists = repository.existsById(id);
                        if (exists) {
                                repository.deleteById(id);
                                return ResponseEntity.status(HttpStatus.OK).body(
                                                new ApiResult(true, 200, "Delete brand successfully ",
                                                                null));
                        }
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                        new ApiResult(false, 404, "Cannot find brand to delete ",
                                                        null));
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                        new ApiResult(false, 400, e.getMessage(), null));
                }
        }

}
