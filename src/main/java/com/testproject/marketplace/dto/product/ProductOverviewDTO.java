package com.testproject.marketplace.dto.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOverviewDTO {

    private String title;

    private String description;

    private Double price;
}
