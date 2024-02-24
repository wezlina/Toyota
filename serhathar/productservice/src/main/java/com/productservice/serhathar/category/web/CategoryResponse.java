package com.productservice.serhathar.category.web;

import com.productservice.serhathar.category.api.CategoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryResponse {

    private String id;
    private String name;
    private String description;
    private Date creaDate;
    private Boolean status;

    public static CategoryResponse toResponse(CategoryDto dto) {
        return CategoryResponse.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .name(dto.getName())
                .description(dto.getDescription())
                .creaDate(dto.getCreaDate())
                .build();
    }
}