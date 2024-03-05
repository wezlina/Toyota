package serhathar.saleservice.inventory.api;


import serhathar.saleservice.Item.api.ItemDto;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    List<InventoryDto> getAllInventories();

    InventoryDto getById(String id);

    List<ItemDto> findItemsByInventoryId(String inventoryId);

    void deleteInventory(String inventoryId);

    InventoryDto updateInventory(String id, InventoryDto dto);

    InventoryDto addProductToInventory(String inventoryId, String productId, Long amount);

    InventoryDto removeProductFromInventory(String inventoryId, String productId, Long amount);
}