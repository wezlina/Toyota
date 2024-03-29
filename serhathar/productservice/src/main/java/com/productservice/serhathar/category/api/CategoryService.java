package com.productservice.serhathar.category.api;

import com.productservice.serhathar.category.impl.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);

    List<CategoryDto> getAllCategory();

    Category getById(String id);

    CategoryDto deleteCategory(String id);

    CategoryDto updateCategory(String id, CategoryDto dto);

    CategoryDto activateCategory(String id);

    Category getByName(String name);

    List<CategoryDto> findByStatusTrue();
}