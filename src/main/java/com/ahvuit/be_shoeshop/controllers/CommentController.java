package com.ahvuit.be_shoeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahvuit.be_shoeshop.entity.Comment;
import com.ahvuit.be_shoeshop.models.ApiResult;
import com.ahvuit.be_shoeshop.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/getAllComments/{id}")
    ResponseEntity<ApiResult> getAllComments(@PathVariable String id) {
        return commentService.getAllComments(id);
    }

    @PostMapping("/api/insertComment")
    ResponseEntity<ApiResult> insertComment(@RequestBody Comment comment) {
        // 2 products must not have the same name !
        return commentService.insertComment(comment);
    }

}
