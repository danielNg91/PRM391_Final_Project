package com.ngoctin.intuition.IntuitionStore.models.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistoryResponse {
    private Long id;
    private String createdDate;
    private float totalPrice;
}
