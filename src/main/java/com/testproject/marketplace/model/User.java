package com.testproject.marketplace.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username",
            nullable = false,
            updatable = false)
    private String username;

    @Column(name = "email",
            unique = true)
    private String email;

    @Column(name = "password",
            length = 2000)
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private URole role;

}
