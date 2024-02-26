package com.productservice.serhathar.category.web;

import com.productservice.serhathar.category.api.CategoryDto;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CategoryRequest {

    private final String name;
    private final String description;
    private final Boolean status;

    public CategoryDto toDto() {
        return CategoryDto.builder()
                .name(name)
                .status(status)
                .description(description)
                .build();
    }
}
