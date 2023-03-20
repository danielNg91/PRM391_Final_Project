package com.ngoctin.intuition.IntuitionStore.entities.product;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private int quantity;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "last_modified")
    private String lastModified;
    private String description;
    @ManyToOne
    @JoinColumn(name = "cate_id",nullable = false)
    private Category category;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "url")
    private String url;
}
