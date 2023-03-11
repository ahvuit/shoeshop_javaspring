package com.ahvuit.be_shoeshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahvuit.be_shoeshop.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> getAllCommentsByProductId(String id);
}
