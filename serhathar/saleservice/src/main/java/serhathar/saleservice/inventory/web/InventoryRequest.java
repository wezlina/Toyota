package serhathar.saleservice.inventory.web;

import serhathar.saleservice.inventory.api.InventoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InventoryRequest {

    private String name;
    private List<String> productIdList;

    public InventoryDto toDto() {
        return InventoryDto.builder()
                .name(name)
                .productIdList(productIdList)
                .build();
    }
}
