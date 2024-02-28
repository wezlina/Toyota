package serhathar.saleservice.Item.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;

@Data
@Builder
public class ItemResponse {
    private String id;
    private String product;
    private Long amount;

    public static ItemResponse toResponse(ItemDto dto) {
        return ItemResponse.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .product(dto.getProduct())
                .build();
    }
}
