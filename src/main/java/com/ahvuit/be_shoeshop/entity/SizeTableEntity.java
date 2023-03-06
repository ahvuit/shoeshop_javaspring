package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;

public class SizeTableEntity {
    @Id
    private String sizeTableId;
    private Integer s38;
    private Integer s39;
    private Integer s40;
    private Integer s41;
    private Integer s42;
    private Integer s43;
    private Integer s44;
    private Integer s45;
    private Integer s46;
    private Integer s47;
    private Integer s48;
    private String productId;

    public String getSizeTableId() {
        return sizeTableId;
    }

    public void setSizeTableId(String sizeTableId) {
        this.sizeTableId = sizeTableId;
    }

    public Integer getS38() {
        return s38;
    }

    public void setS38(Integer s38) {
        this.s38 = s38;
    }

    public Integer getS39() {
        return s39;
    }

    public void setS39(Integer s39) {
        this.s39 = s39;
    }

    public Integer getS40() {
        return s40;
    }

    public void setS40(Integer s40) {
        this.s40 = s40;
    }

    public Integer getS41() {
        return s41;
    }

    public void setS41(Integer s41) {
        this.s41 = s41;
    }

    public Integer getS42() {
        return s42;
    }

    public void setS42(Integer s42) {
        this.s42 = s42;
    }

    public Integer getS43() {
        return s43;
    }

    public void setS43(Integer s43) {
        this.s43 = s43;
    }

    public Integer getS44() {
        return s44;
    }

    public void setS44(Integer s44) {
        this.s44 = s44;
    }

    public Integer getS45() {
        return s45;
    }

    public void setS45(Integer s45) {
        this.s45 = s45;
    }

    public Integer getS46() {
        return s46;
    }

    public void setS46(Integer s46) {
        this.s46 = s46;
    }

    public Integer getS47() {
        return s47;
    }

    public void setS47(Integer s47) {
        this.s47 = s47;
    }

    public Integer getS48() {
        return s48;
    }

    public void setS48(Integer s48) {
        this.s48 = s48;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public SizeTableEntity() {

    }

    public SizeTableEntity(String sizeTableId, Integer s38, Integer s39, Integer s40, Integer s41, Integer s42,
            Integer s43,
            Integer s44, Integer s45, Integer s46, Integer s47, Integer s48, String productId) {
        this.sizeTableId = sizeTableId;
        this.s38 = s38;
        this.s39 = s39;
        this.s40 = s40;
        this.s41 = s41;
        this.s42 = s42;
        this.s43 = s43;
        this.s44 = s44;
        this.s45 = s45;
        this.s46 = s46;
        this.s47 = s47;
        this.s48 = s48;
        this.productId = productId;
    }
}
