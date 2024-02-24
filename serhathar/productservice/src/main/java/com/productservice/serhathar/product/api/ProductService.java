package com.productservice.serhathar.product.api;

import com.productservice.serhathar.product.impl.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto createProduct(ProductDto dto);
    List<ProductDto> getAllProducts();
    List<ProductDto> findProductByStatusIsTrue();
    String deleteProduct(String id);
    String activateProduct(String id);
    ProductDto updateProduct(String id , ProductDto product);
    List<ProductDto> getProductByCategory(String categoryName);
    Product getProductById(String id);
    List<ProductDto> findAllByStatusAndCategoryStatus(Boolean productStatus, Boolean categoryStatus);
    List<ProductDto> findProductById(String id);
}