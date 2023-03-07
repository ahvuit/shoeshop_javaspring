package com.ahvuit.be_shoeshop.models;

import java.util.Date;

import com.ahvuit.be_shoeshop.entity.Comment;

public class CommentModel extends Comment {

    public CommentModel(String cmtId, String productId, String userId, String content, String image, Date createDate) {
        super(cmtId, productId, userId, content, image, createDate);
    }

    public CommentModel() {
    }

}
