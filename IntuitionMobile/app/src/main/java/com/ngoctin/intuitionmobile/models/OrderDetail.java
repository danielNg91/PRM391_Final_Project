package com.ngoctin.intuitionmobile.models;

public class OrderDetail {

    private Long id;
    private int productID;
    private int quantity;
    private float price;
    private Long orderID;

    public OrderDetail(Long id, int productID, int quantity, float price, Long orderID) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.orderID = orderID;
    }


    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderID=" + orderID +
                '}';
    }
}
