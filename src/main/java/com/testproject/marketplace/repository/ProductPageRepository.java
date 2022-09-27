package com.testproject.marketplace.repository;

import com.testproject.marketplace.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPageRepository extends PagingAndSortingRepository<Product,Long> {
}
