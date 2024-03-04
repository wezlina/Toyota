package serhathar.saleservice.Item.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;

@Data
@Builder
public class ItemRequest {

    private String productId;
    private Long amount;
    private Boolean status;
    public ItemDto toDto() {
        return ItemDto.builder()
                .amount(amount)
                .status(status)
                //.productId(productId)
                .build();
    }
}