package com.ahvuit.be_shoeshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.SizeTable;

public interface SizeTableRepository extends MongoRepository<SizeTable, String> {

}