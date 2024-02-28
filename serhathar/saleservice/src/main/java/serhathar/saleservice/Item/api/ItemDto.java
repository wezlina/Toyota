package serhathar.saleservice.Item.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {

    private String id;
    private String product;
    private Long amount;
}
