package com.shop.nucknuckshop.user.domain;

import javax.persistence.*;

@Entity
@Table(name = "NNS_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
