package serhathar.saleservice.inventory.api;


import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    List<InventoryDto> getAllInventories();

    InventoryDto getById(String id);

    InventoryDto updateInventory(String id, InventoryDto dto);

    void addProductToInventory(String inventoryId, String productId, Long amount);

    void removeProductFromInventory(String inventoryId, String productId, Long amount);
}