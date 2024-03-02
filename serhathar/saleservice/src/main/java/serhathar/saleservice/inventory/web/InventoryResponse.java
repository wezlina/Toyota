package serhathar.saleservice.inventory.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.inventory.api.InventoryDto;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private String id;
    private String name;
    private List<ItemDto> productList;

    public static InventoryResponse toResponse(InventoryDto dto) {
        return InventoryResponse.builder()
                .id(dto.getId())
                .productList(dto.getProductList())
                .name(dto.getName())
                .build();
    }
}