package com.ngoctin.intuition.IntuitionStore.entities.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private Long id;
    @Column(name = "user_id")
    private int userID;
    @Column(name = "promotion_id")
    private int promotionId;
    @Column(name = "addr_id")
    private int addressId;
    @Column(name = "order_price")
    private float orderPrice;
    @Column(name = "created_date")
    private String createDate;
    @Column(name = "receiver_address")
    private String receriverAddress;

    public Order(Long id, int userID, int promotionId, int addressId, float orderPrice) {
        this.id = id;
        this.userID = userID;
        this.promotionId = promotionId;
        this.addressId = addressId;
        this.orderPrice = orderPrice;
    }

    public Order(Long id, int userID, int promotionId, int addressId,
                 float orderPrice, String receriverAddress) {
        this.id = id;
        this.userID = userID;
        this.promotionId = promotionId;
        this.addressId = addressId;
        this.orderPrice = orderPrice;
        this.receriverAddress = receriverAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReceriverAddress() {
        return receriverAddress;
    }

    public void setReceriverAddress(String receriverAddress) {
        this.receriverAddress = receriverAddress;
    }
}
