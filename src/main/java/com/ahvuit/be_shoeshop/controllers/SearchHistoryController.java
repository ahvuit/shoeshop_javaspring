package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.SearchHistory;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.SearchHistoryService;

@RestController
public class SearchHistoryController {
    @Autowired
    private SearchHistoryService searchHistoryService;

    @GetMapping("/api/getProductBySearch/{id}")
    ResponseEntity<ApiResult> getProductBySearch(@PathVariable String id) {
        return searchHistoryService.getProductBySearch(id);
    }

    @PutMapping("/api/insertOrUpdateKeyword")
    ResponseEntity<ApiResult> insertOrUpdateKeyword(@RequestBody SearchHistory searchHistory) {
        return searchHistoryService.insertOrUpdateKeyword(searchHistory);
    }

}
