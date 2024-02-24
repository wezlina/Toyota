package com.productservice.serhathar.inventory.web;

import com.productservice.serhathar.inventory.api.InventoryDto;
import com.productservice.serhathar.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private String id;
    private String name;
    private List<ProductDto> productList;

    public static InventoryResponse toResponse(InventoryDto dto) {
        return InventoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .productList(dto.getProductList())
                .build();
    }
}
