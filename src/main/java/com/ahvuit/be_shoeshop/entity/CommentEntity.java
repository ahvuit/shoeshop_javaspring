package com.ahvuit.be_shoeshop.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class CommentEntity {
    @Id
    private String cmtId;
    private String productId;
    private String userId;
    private String content;
    private String image;
    private Date createDate;

    public CommentEntity() {
    }

    public CommentEntity(String cmtId, String productId, String userId, String content, String image, Date createDate) {
        this.cmtId = cmtId;
        this.productId = productId;
        this.userId = userId;
        this.content = content;
        this.image = image;
        this.createDate = createDate;
    }

    public String getCmtId() {
        return cmtId;
    }

    public void setCmtId(String cmtId) {
        this.cmtId = cmtId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
