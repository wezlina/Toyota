package serhathar.saleservice.inventory.api;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class InventoryDto {

    //every inventory is uniq for every user
    private String id;
    private String name;
    private List<ProductDto> producList = new ArrayList<>();
}
