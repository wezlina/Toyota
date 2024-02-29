package serhathar.saleservice.inventory.web;

import serhathar.saleservice.Item.impl.Item;
import serhathar.saleservice.inventory.api.InventoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private String id;
    private String name;
    private List<Item> productList;

    public static InventoryResponse toResponse(InventoryDto dto) {
        return InventoryResponse.builder()
                .id(dto.getId())
                .productList(dto.getProductList())
                .name(dto.getName())
                .build();
    }
}