package serhathar.saleservice.Item.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;

@Data
@Builder
public class ItemRequest {
    private String id;
    private String product;
    private Long amount;

    public ItemDto toDto() {
        return ItemDto.builder()
                .id(id)
                .amount(amount)
                .product(product)
                .build();
    }
}
