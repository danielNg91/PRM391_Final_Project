package com.ngoctin.intuition.IntuitionStore.models.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    @Id
    private Long id;
    private String productName;
    private int quantity;
    private float price;

}
