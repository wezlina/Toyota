package serhathar.saleservice.order.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.order.api.OrderDto;

import java.math.BigDecimal;

@Data
@Builder
public class OrderRequest {

    String inventoryId;
    BigDecimal totalPrice;

    public OrderDto toDto() {
        return OrderDto.builder()
                .inventoryId(inventoryId)
                .totalPrice(totalPrice)
                .build();
    }
}
