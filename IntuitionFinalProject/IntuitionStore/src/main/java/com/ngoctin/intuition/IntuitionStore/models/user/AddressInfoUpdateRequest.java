package com.ngoctin.intuition.IntuitionStore.models.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoUpdateRequest {
    private Long id;
    private String Address;
}
