package serhathar.saleservice.inventory.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

    Optional<Object> findByName(String name);

    Inventory getInventoryById(String id);
}