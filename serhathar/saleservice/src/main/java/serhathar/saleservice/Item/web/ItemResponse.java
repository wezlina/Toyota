package serhathar.saleservice.Item.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.Item.api.ItemDto;

@Data
@Builder
public class ItemResponse {

    private String id;
    private String productId;
    private Long amount;

    public static ItemResponse toResponse(ItemDto dto) {
        return ItemResponse.builder()
                .id(dto.getId())
                .productId(dto.getProductId())
                .amount(dto.getAmount())
                .build();
    }
}
