package com.ngoctin.intuition.IntuitionStore.entities.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {

    @Id
    private Long id;
    @Column(name = "product_id")
    private int productID;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "order_detail_price")
    private float price;
    @Column(name = "order_id")
    private Long orderID;
}
