package com.testproject.marketplace.service;

import com.testproject.marketplace.dto.product.ProductDTO;
import com.testproject.marketplace.dto.product.ProductOverviewDTO;
import com.testproject.marketplace.dto.product.ProductRequestDTO;
import com.testproject.marketplace.model.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductService {

    List<ProductOverviewDTO> findByPaginate(int pageNumber, int pageSize);

    List<ProductOverviewDTO> findAllProducts();

    void createProductByUsername(ProductRequestDTO productRequestDTO, String username);

    void editProductByUsername(Long id, ProductRequestDTO productRequestDTO, String username);

    void deleteProductByUsername(Long id, String username);

    ProductDTO findProductByUsernameId(Long id);

    List<ProductDTO> findAllProductsByUsername(String username);

    Product likeProduct(Long productId, String username);

    Product unlikeProduct(Long productId, String username);

}
