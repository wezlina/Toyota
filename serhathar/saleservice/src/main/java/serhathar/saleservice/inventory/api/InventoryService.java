package serhathar.saleservice.inventory.api;

import serhathar.saleservice.inventory.impl.InventoryRepository;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    List<InventoryDto> getAllInventories();

    InventoryDto getById(String id);

    InventoryRepository updateInventory(String id, InventoryDto dto);

    void addProductToInventory(String inventoryId, String productId);

    void removeProductFromInventory(String inventoryId, String productId);
}
