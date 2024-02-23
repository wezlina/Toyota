package com.productservice.serhathar.category.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String name);
    Category getCategoryById(String id);
    List<Category> findAllByStatusTrue();
}