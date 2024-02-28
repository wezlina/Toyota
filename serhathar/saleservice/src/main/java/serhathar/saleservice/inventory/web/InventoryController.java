package serhathar.saleservice.inventory.web;

import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{inventoryId}/products/{productId}/add-{amount}")
    public void addProductToInventory(@PathVariable String inventoryId, @PathVariable String productId, @PathVariable Long amount) {
        service.addProductToInventory(inventoryId, productId, amount);
    }

    @PutMapping("/{inventoryId}/products/{productId}/remove")
    public void removeProductFromInventory(@PathVariable String inventoryId, @PathVariable String productId) {
        service.removeProductFromInventory(inventoryId, productId);
    }

    public List<InventoryResponse> toResponse(List<InventoryDto> inventoryDtoList) {
        return inventoryDtoList.stream().map(InventoryResponse::toResponse).toList();
    }
}