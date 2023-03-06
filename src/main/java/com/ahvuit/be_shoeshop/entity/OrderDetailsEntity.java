package com.ahvuit.be_shoeshop.entity;

public class OrderDetailsEntity {

    private String orderId;
    private String productId;
    private String quantity;
    private String size;
    private Double price;
    private String total;

    public OrderDetailsEntity() {
    }

    public OrderDetailsEntity(String orderId, String productId, String quantity, String size, Double price,
            String total) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
