package com.testproject.marketplace.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description",
            length = 500)
    private String description;

    @Column(name = "price")
    private Double price;


    @Column(name = "likes")
    private Integer likes;

    @Column(name = "unlikes")
    private Integer unlikes;


    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> unlikedUsers = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
