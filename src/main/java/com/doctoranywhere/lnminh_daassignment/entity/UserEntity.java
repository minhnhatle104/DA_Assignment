package com.doctoranywhere.lnminh_daassignment.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
