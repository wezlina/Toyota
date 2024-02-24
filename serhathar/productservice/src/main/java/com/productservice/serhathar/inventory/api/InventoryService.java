package com.productservice.serhathar.inventory.api;

import com.productservice.serhathar.inventory.impl.InventoryRepository;

import java.util.List;

public interface InventoryService {
    InventoryDto createInventory(InventoryDto inventoryDto);

    List<InventoryDto> getAllInventories();

    InventoryDto getById(String id);

    InventoryRepository updateInventory(String id, InventoryDto dto);

    int addProductToInventory(String inventoryId, String productId);

    void removeProductFromInventory(String inventoryId, String productId);
}
