package com.ngoctin.intuition.IntuitionStore.models.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private Product product;
    private Long cateID;

}
