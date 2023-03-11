package com.ahvuit.be_shoeshop.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.SearchHistory;

public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String> {
    Optional<SearchHistory> getSearchHistoryByUserId(String userId);
}
