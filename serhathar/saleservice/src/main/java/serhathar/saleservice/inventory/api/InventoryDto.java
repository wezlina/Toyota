package serhathar.saleservice.inventory.api;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class InventoryDto {

    private String id;
    private String name;
    private List<ItemDto> productList = new ArrayList<>();
}