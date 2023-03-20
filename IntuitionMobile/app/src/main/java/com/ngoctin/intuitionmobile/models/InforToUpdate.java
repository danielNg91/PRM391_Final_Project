package com.ngoctin.intuitionmobile.models;

public class InforToUpdate {

    private String fullname;
    private String phoneNumber;
    private String email;


    public InforToUpdate() {
    }

    public InforToUpdate(String fullname, String phoneNumber, String email) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
