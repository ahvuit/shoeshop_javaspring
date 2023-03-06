package com.ahvuit.be_shoeshop.models;

import java.util.List;

public class OrderOrderDetails {
    private Order order;
    private List<OrderDetails> listOrderDetails;

    public OrderOrderDetails(Order order, List<OrderDetails> listOrderDetails) {
        this.order = order;
        this.listOrderDetails = listOrderDetails;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetails> getListOrderDetails() {
        return listOrderDetails;
    }

    public void setListOrderDetails(List<OrderDetails> listOrderDetails) {
        this.listOrderDetails = listOrderDetails;
    }
}
