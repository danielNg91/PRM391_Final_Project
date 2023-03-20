package com.ngoctin.intuition.IntuitionStore.entities.promotion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "discount_percent")
    private int discountPercent;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "issue_date")
    private String issuedDate;
}
