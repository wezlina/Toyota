package serhathar.saleservice.inventory.web;

import serhathar.saleservice.inventory.api.InventoryDto;
import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.inventory.api.ProductDto;

import java.util.List;

@Data
@Builder
public class InventoryRequest {

    private String name;
    private List<ProductDto> productList;

    public InventoryDto toDto() {
        return InventoryDto.builder()
                .name(name)
                .productDtoList(productList)
                .build();
    }
}