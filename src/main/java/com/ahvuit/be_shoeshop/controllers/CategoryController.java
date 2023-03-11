package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Category;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.CategoryService;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/getAllCategories")
    ResponseEntity<ApiResult> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/api/getCategoryDetails/{id}")
    ResponseEntity<ApiResult> findById(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @PostMapping("/api/insertCategory")
    ResponseEntity<ApiResult> insertCategory(@RequestBody Category category) {
        // 2 products must not have the same name !
        return categoryService.insertCategory(category);
    }

    @PutMapping("/api/updateCategory/{id}")
    public ResponseEntity<ApiResult> updateCategory(@RequestBody Category category, @PathVariable String id) {
        return categoryService.updateCategory(category, id);
    }
}
