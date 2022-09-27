package com.testproject.marketplace.repository;

import com.testproject.marketplace.model.Product;
import com.testproject.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByUser(User user);
}
