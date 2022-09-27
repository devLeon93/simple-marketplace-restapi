package com.testproject.marketplace.mapper;

import com.testproject.marketplace.dto.product.ProductLikeDTO;
import com.testproject.marketplace.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductLikeDTOMapper {

    public ProductLikeDTO mapper(Product product){

        final ProductLikeDTO productLikeDTO = new ProductLikeDTO();

        productLikeDTO.setUsersLiked(product.getLikedUsers());
        productLikeDTO.setUsersUnliked(product.getUnlikedUsers());

        return productLikeDTO;

    }
}
