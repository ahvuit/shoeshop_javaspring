package com.ahvuit.be_shoeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Category;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<ApiResult> getAllCategories() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query category successfully", categoryRepository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> findById(String id) {
        try {
            Optional<Category> foundCategory = categoryRepository.findById(id);
            return foundCategory.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Query category successfully", foundCategory))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new ApiResult(false, 404, "Cannot find category", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertCategory(Category category) {
        try {
            List<Category> foundCategories = categoryRepository.findByCategoryName(category.getCategoryName());
            return foundCategories.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "insert new category successfully",
                            categoryRepository.save(category)))
                    : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                            new ApiResult(false, 404, "category name is already", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> updateCategory(Category newCategory, String id) {
        try {
            Category updatedCategory = categoryRepository.findById(id)
                    .map(category -> {
                        category.setCategoryName(newCategory.getCategoryName());
                        return categoryRepository.save(category);
                    }).orElseGet(() -> {
                        newCategory.setCategoryId(id);
                        return categoryRepository.save(newCategory);
                    });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Update category successfully",
                            updatedCategory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

}
