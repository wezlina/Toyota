package serhathar.saleservice.inventory.api;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.impl.Item;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class InventoryDto {

    private String id;
    private String name;
    private List<Item> productList = new ArrayList<>();
}