package serhathar.saleservice.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.Item.impl.ItemServiceImpl;
import serhathar.saleservice.inventory.api.InventoryService;
import serhathar.saleservice.order.api.OrderDto;
import serhathar.saleservice.order.api.OrderService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final InventoryService inventoryService;
    private final OrderRepository repository;
    private final ItemServiceImpl itemService;

    @Override
    @Transactional
    public OrderDto sellInventory(OrderDto dto, String inventoryId) {
        OrderEntity order = toEntity(dto);
        order.setInventoryId(inventoryId);
        order.setTotalPrice(calculateInventoryPrice(inventoryId));//inventory satisi yapildiktan sonra status 0 olmali
        return toDto(repository.save(order));
        /*Inventory inventory = toEntity(inventoryDto);
        return toDto(repository.save(inventory));*/
    }

    @Override
    public BigDecimal calculateInventoryPrice(String inventoryId) {
        int productListSize = inventoryService.getById(inventoryId).getProductList().size();
        BigDecimal totalPrice = new BigDecimal(0);
        for (int i = 0; i < productListSize; i++) {
            BigDecimal productPrice = inventoryService.getById(inventoryId).getProductList().get(i).getProduct().getPrice();
            Long productAmount = inventoryService.getById(inventoryId).getProductList().get(i).getAmount();
            BigDecimal itemPrice = productPrice.multiply(BigDecimal.valueOf(productAmount));
            totalPrice = totalPrice.add(itemPrice);
        }
        return totalPrice;
    }

    private OrderEntity toEntity(OrderDto dto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setTotalPrice(dto.getTotalPrice());
        orderEntity.setInventoryId(dto.getInventoryId());
        return orderEntity;
    }

    private OrderDto toDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .inventoryId(orderEntity.getInventoryId())
                .totalPrice(orderEntity.getTotalPrice())
                .build();
    }
}

