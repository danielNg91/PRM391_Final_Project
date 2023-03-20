package com.ngoctin.intuition.IntuitionStore.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalResponse {

    private int id;
    private String username;
    private String fullname;
    private String phoneNumber;
    private String avatar;
    private String rank;
    private String email;
    private String role;
}
