package com.productservice.serhathar.inventory.api;

import com.productservice.serhathar.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InventoryDto {

    //every inventory is uniq for every user
    private String id;
    private String name;
    private List<ProductDto> productList;
}
