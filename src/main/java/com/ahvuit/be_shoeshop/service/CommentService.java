package com.ahvuit.be_shoeshop.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahvuit.be_shoeshop.entity.Comment;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public ResponseEntity<ApiResult> getAllComments() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Successfully", commentRepository.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResult> insertComment(Comment comment) {
        try {
            comment.setCreateDate(new Date());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResult(true, 200, "Successfully",
                            commentRepository.save(comment)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResult(false, 400, e.getMessage(), null));
        }
    }
}
