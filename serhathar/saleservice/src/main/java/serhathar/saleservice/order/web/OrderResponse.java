package serhathar.saleservice.order.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.order.api.OrderDto;

import java.math.BigDecimal;

@Data
@Builder
public class OrderResponse {

    String id;
    String inventoryId;
    BigDecimal totalPrice;

    public static OrderResponse toResponse(OrderDto dto) {
        return OrderResponse.builder()
                .id(dto.getId())
                .inventoryId(dto.getInventoryId())
                .totalPrice(dto.getTotalPrice())
                .build();
    }
}
