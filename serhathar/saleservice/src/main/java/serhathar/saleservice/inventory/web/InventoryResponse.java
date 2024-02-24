package serhathar.saleservice.inventory.web;

import serhathar.saleservice.inventory.api.InventoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private String id;
    private String name;
    private List<String> productIdList;

    public static InventoryResponse toResponse(InventoryDto dto) {
        return InventoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .productIdList(dto.getProductIdList())
                .build();
    }
}
