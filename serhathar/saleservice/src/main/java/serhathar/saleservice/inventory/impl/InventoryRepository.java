package serhathar.saleservice.inventory.impl;

import serhathar.saleservice.inventory.api.InventoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import serhathar.saleservice.inventory.api.ProductDto;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Optional<Object> findByName(String name);

    Inventory getInventoryById(String id);
}
