package com.ngoctin.intuitionmobile.models;

import java.io.Serializable;

public class Address implements Serializable  {
    private Long id;
    private String value;

    public Address() {
    }

    public Address(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Address(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
