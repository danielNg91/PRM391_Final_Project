package com.ngoctin.intuition.IntuitionStore.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {
    private Long id;
    private String username;
    private String fullname;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
}

