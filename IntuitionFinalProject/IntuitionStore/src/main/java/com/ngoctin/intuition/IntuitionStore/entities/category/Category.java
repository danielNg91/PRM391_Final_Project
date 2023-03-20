package com.ngoctin.intuition.IntuitionStore.entities.category;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "last_modified")
    private String lastModified;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
