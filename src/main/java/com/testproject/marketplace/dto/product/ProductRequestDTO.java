package com.testproject.marketplace.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDTO {

    @NotEmpty(message = "Please enter the product description")
    private String productDescription;

    @NotEmpty(message = "Please enter the title")
    private String productTitle;

    @NotNull(message = "Please enter the price product")
    private Double productPrice;

}
