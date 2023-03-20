package com.ngoctin.intuitionmobile.models;

import java.io.Serializable;

public class AuthenticatedUser implements Serializable {

    private int id;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String avatar;
    private String rank;
    private String email;
    private String role;
    private String jwt;


    public AuthenticatedUser(int id, String username,
                             String fullname, String phoneNumber,
                             String avatar, String rank,
                             String email, String role,
                             String jwt) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.rank = rank;
        this.email = email;
        this.role = role;
        this.jwt = jwt;
    }

    public AuthenticatedUser(String username, String fullname,
                             String phoneNumber, String avatar,
                             String rank, String email,
                             String role, String jwt) {
        this.username = username;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.rank = rank;
        this.email = email;
        this.role = role;
        this.jwt = jwt;
    }

    public AuthenticatedUser() {
    }

    public int getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{" +
                "username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", rank='" + rank + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
