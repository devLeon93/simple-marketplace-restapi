package com.testproject.marketplace.controller;

import com.testproject.marketplace.dto.product.ProductDTO;
import com.testproject.marketplace.dto.product.ProductLikeDTO;
import com.testproject.marketplace.dto.product.ProductOverviewDTO;
import com.testproject.marketplace.dto.product.ProductRequestDTO;
import com.testproject.marketplace.mapper.ProductDTOMapper;
import com.testproject.marketplace.mapper.ProductLikeDTOMapper;
import com.testproject.marketplace.model.Product;
import com.testproject.marketplace.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDTOMapper productDTOMapper;

    private final ProductLikeDTOMapper productLikeDTOMapper;


    @Operation(summary = "Create product")
    @PostMapping
    public ResponseEntity<Void> add(@Validated @RequestBody ProductRequestDTO productRequestDTO,
                                    Principal principal) {
        productService.createProductByUsername(productRequestDTO, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Like Product")
    @PatchMapping("/{productId}/{username}/like")
    public ResponseEntity<ProductLikeDTO> likeProduct(@PathVariable("productId") String productId,
                                                      @PathVariable("username") String username,
                                                      Principal principal) {
        Product product = productService.likeProduct(Long.parseLong(productId), principal.getName());
        ProductLikeDTO productLikeDTO = productLikeDTOMapper.mapper(product);
        return new ResponseEntity<>(productLikeDTO, HttpStatus.OK);
    }


   @Operation(summary = "Unlike Product")
    @PatchMapping("/{productId}/{username}/unlike")
    public ResponseEntity<ProductDTO> unlikeProduct(@PathVariable("productId") String productId,
                                                    @PathVariable("username") String username,
                                                    Principal principal) {
        Product product = productService.unlikeProduct(Long.parseLong(productId), principal.getName());
        ProductDTO productDTO = productDTOMapper.mapper(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @Operation(summary = "Edit product")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductRequestDTO productRequestDTO,
                                    @PathVariable Long id,
                                    Principal principal) {
        productService.editProductByUsername(id, productRequestDTO, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           Principal principal) {
        productService.deleteProductByUsername(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "View product")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.findProductByUsernameId(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);    }


    @Operation(summary = "List my-products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductsByUser(Principal principal) {
        List<ProductDTO> productDTOS = productService.findAllProductsByUsername(principal.getName());
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @Operation(summary = "List Products")
    @GetMapping("/all")
    public ResponseEntity<List<ProductOverviewDTO>> findAllProducts() {
        List<ProductOverviewDTO> productDTOS = productService.findAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @Operation(summary = "Pagination products")
    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<List<ProductOverviewDTO>> paginate (@PathVariable("pageNo") int pageNumber,
                                                              @PathVariable("pageSize") int pageSize) {
        List<ProductOverviewDTO> productPageDTOS = productService.findByPaginate(pageNumber, pageSize);
        return new ResponseEntity<>(productPageDTOS, HttpStatus.OK);
    }

}
