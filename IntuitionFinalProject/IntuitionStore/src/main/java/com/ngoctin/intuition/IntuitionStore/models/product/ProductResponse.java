package com.ngoctin.intuition.IntuitionStore.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private int id;
    private String name;
    private String price;
    private int quantity;
    private String description;
    String url;
}
