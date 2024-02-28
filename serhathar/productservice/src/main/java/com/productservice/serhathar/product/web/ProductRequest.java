package com.productservice.serhathar.product.web;

import com.productservice.serhathar.category.api.CategoryDto;
import com.productservice.serhathar.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class ProductRequest {
    private final String name;
    private final String brand;
    private final BigDecimal price;
    private final CategoryDto category;
    private final String categoryName;
    private final String description;
    private final String barcode;
    private Long amount;
    private final Boolean status;

    public ProductDto toDto() {
        return ProductDto.builder()
                .name(name)
                .brand(brand)
                .amount(amount)
                .status(status)
                .description(description)
                .price(price)
                .category(category)
                .categoryName(categoryName)
                .barcode(barcode)
                .build();
    }
}