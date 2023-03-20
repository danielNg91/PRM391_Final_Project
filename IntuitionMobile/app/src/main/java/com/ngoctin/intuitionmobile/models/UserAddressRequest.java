package com.ngoctin.intuitionmobile.models;

public class UserAddressRequest {

    private boolean isDeleted;

    public UserAddressRequest() {
    }

    public UserAddressRequest(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
