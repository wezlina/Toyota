package com.productservice.serhathar.inventory.impl;

import com.productservice.serhathar.inventory.api.InventoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Optional<Object> findByName(String name);

    Inventory getInventoryById(String id);

    List<InventoryDto> getInventoriesById(String id);
}
