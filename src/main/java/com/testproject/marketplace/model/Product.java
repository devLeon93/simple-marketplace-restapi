package com.testproject.marketplace.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
