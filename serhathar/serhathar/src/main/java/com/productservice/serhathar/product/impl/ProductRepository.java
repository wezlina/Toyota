package com.productservice.serhathar.product.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Object> findByName(String name);
    List<Product> findAllByStatusTrue();
    List<Product> findAllByStatusAndCategoryStatus(Boolean productStatus, Boolean categoryStatus);
    Product getProductById(String id);
}