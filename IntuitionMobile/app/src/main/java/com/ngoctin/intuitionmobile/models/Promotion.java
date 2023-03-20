package com.ngoctin.intuitionmobile.models;

public class Promotion {

    private int id;
    private String name;
    private int discountPercent;
    private String createdDate;
    private String issuedDate;

    public Promotion(int id, String name, int discountPercent, String createdDate, String issuedDate) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
        this.createdDate = createdDate;
        this.issuedDate = issuedDate;
    }

    public Promotion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discountPercent=" + discountPercent +
                ", createdDate='" + createdDate + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                '}';
    }
}
