package com.testproject.marketplace.mapper;

import com.testproject.marketplace.dto.product.ProductOverviewDTO;
import com.testproject.marketplace.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductOverviewDTOMapper {

    public ProductOverviewDTO mapper(Product product) {

        final ProductOverviewDTO productOverviewDTO = new ProductOverviewDTO();

        productOverviewDTO.setTitle(product.getTitle());
        productOverviewDTO.setDescription(product.getDescription());
        productOverviewDTO.setPrice(product.getPrice());

        return productOverviewDTO;
    }
}
