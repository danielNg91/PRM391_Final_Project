package com.ngoctin.intuitionmobile.models;

public class Category {

    private Long id;
    private String name;
    private String createDate;
    private String lastModified;
    private boolean deleted;

    public Category(Long id, String name, String createDate, String lastModified, boolean deleted) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.lastModified = lastModified;
        this.deleted = deleted;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
