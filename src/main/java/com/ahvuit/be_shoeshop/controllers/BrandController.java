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
import com.ahvuit.be_shoeshop.models.Brand;
import com.ahvuit.be_shoeshop.service.BrandService;

@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/api/getAllBrands")
    ResponseEntity<ApiResult> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/api/getBrandById/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return brandService.findById(id);
    }

    @PostMapping("/api/insertBrand")
    ResponseEntity<ApiResult> insertBrand(@RequestBody Brand brand) {
        // 2 products must not have the same name !
        return brandService.insertBrand(brand);
    }

    @PutMapping("/api/updateBrand/{id}")
    public ResponseEntity<ApiResult> updateProduct(@RequestBody Brand brand, @PathVariable String id) {
        return brandService.updateBrand(brand, id);
    }

    @DeleteMapping("/api/deleteBrand/{id}")
    public ResponseEntity<ApiResult> deleteTask(@PathVariable String id) {
        return brandService.deleteBrand(id);
    }
}
