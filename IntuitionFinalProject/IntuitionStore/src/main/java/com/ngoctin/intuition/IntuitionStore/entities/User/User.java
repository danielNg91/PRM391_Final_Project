package com.ngoctin.intuition.IntuitionStore.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullname;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "last_modified")
    private String lastModified;
    private String avatar;
    private String rank;
    private String role;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
