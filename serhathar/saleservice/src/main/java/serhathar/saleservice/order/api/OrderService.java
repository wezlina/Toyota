package serhathar.saleservice.order.api;

import java.math.BigDecimal;

public interface OrderService {
    OrderDto sellInventory(OrderDto dto, String inventoryId);

    BigDecimal calculateInventoryPrice(String inventoryId);
}
