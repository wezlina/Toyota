package serhathar.saleservice.inventory.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.impl.Item;
import serhathar.saleservice.inventory.api.InventoryDto;

import java.util.List;

@Data
@Builder
public class InventoryRequest {
    //private final InventoryServiceImpl service;

    private String name;
    private List<Item> productList;
    private Boolean status;

    public InventoryDto toDto() {
        return InventoryDto.builder()
                .name(name)
                .status(status)
                //.productList
                .build();
    }
}