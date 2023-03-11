package com.ahvuit.be_shoeshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
