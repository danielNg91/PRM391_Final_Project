package com.ngoctin.intuitionmobile.models;

public class OrderHistory {
    private Long id;
    private String createdDate;
    private float totalPrice;

    public OrderHistory(Long id, String createdDate, float totalPrice) {
        this.id = id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
