package com.ahvuit.be_shoeshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Product;
import com.ahvuit.be_shoeshop.entity.SearchHistory;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.ProductRepository;
import com.ahvuit.be_shoeshop.repositories.SearchHistoryRepository;

@Service
public class SearchHistoryService {
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ApiResult> insertOrUpdateKeyword(SearchHistory newKeyword) {
        try {
            Optional<SearchHistory> insertOrUpdateKeyword = searchHistoryRepository
                    .getSearchHistoryByUserId(newKeyword.getUserId());
            if (insertOrUpdateKeyword.isPresent()) {
                insertOrUpdateKeyword.get().setKeyword(newKeyword.getKeyword());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Successfully",
                                searchHistoryRepository.save(insertOrUpdateKeyword.get())));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Successfully",
                            searchHistoryRepository.save(newKeyword)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> getProductBySearch(String id) {
        try {
            Optional<SearchHistory> searchHistory = searchHistoryRepository
                    .getSearchHistoryByUserId(id);
            List<Product> products = productRepository.findAll();
            List<Product> newProducts = new ArrayList<Product>();
            if (searchHistory.isPresent()) {
                System.out.println(searchHistory.get().getKeyword());
                for (Product product : products) {
                    if (product.getName().toUpperCase()
                            .contains(searchHistory.get().getKeyword().toUpperCase().trim())) {
                        newProducts.add(product);
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ApiResult(true, 200, "Successfully", newProducts));
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }

    }
}
