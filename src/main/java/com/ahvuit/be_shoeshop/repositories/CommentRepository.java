package com.ahvuit.be_shoeshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
