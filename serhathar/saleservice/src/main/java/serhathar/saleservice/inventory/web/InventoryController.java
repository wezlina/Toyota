package serhathar.saleservice.inventory.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/sale-service")
public class InventoryController {
    private final InventoryService service;

    @PostMapping(path = "/add")
    public ResponseEntity<InventoryResponse> createInventory(@Valid @RequestBody InventoryRequest request) {
        InventoryDto inventory = service.createInventory(request.toDto());
        return ResponseEntity.ok(InventoryResponse.toResponse(inventory));
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<InventoryResponse>> getInventory() {
        List<InventoryResponse> inventoryResponseList = toResponse(service.getAllInventories());
        return ResponseEntity.ok(inventoryResponseList);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable @Valid String id) {
        InventoryDto inventory = service.getById(id);
        return ResponseEntity.ok(InventoryResponse.toResponse(inventory));
    }

    @PutMapping("/{inventoryId}/products/{productId}/add_{amount}")
    public ResponseEntity<InventoryResponse> addProductToInventory(@PathVariable String inventoryId, @PathVariable String productId, @PathVariable Long amount) {
        InventoryDto dto = service.addProductToInventory(inventoryId, productId, amount);
        return ResponseEntity.ok(InventoryResponse.toResponse(dto));
    }

    @PutMapping("/{inventoryId}/products/{productId}/remove_{amount}")
    public ResponseEntity<InventoryResponse> removeProductFromInventory(@PathVariable String inventoryId, @PathVariable String productId, @PathVariable Long amount) {
        InventoryDto dto = service.removeProductFromInventory(inventoryId, productId, amount);
        return ResponseEntity.ok(InventoryResponse.toResponse(dto));
    }

    public List<InventoryResponse> toResponse(List<InventoryDto> inventoryDtoList) {
        return inventoryDtoList.stream().map(InventoryResponse::toResponse).toList();
    }
}