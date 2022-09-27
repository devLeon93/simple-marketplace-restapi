package com.testproject.marketplace.mapper;

import com.testproject.marketplace.dto.product.ProductDTO;
import com.testproject.marketplace.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper {

    public ProductDTO mapper(Product product){

        final ProductDTO productDTO = new ProductDTO();

        productDTO.setUsername(product.getUser().getUsername());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());

        return productDTO;

    }

}
