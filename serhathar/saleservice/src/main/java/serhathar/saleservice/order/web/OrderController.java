package serhathar.saleservice.order.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serhathar.saleservice.order.api.OrderDto;
import serhathar.saleservice.order.api.OrderService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/sale-service/orders")
public class OrderController {
    private final OrderService service;

    @PostMapping(path = "/sell_{inventoryId}")
    public ResponseEntity<OrderResponse> sellInventory(@Valid @RequestBody OrderRequest request, @PathVariable String inventoryId) {
        OrderDto orderDto = service.sellInventory(request.toDto(), inventoryId);
        return ResponseEntity.ok(OrderResponse.toResponse(orderDto));
    }

    public List<OrderResponse> toResponse(List<OrderDto> orderDtoList) {
        return orderDtoList.stream().map(OrderResponse::toResponse).toList();
    }
}

