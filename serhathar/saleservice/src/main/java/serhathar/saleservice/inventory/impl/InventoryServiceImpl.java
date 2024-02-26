package serhathar.saleservice.inventory.impl;

import lombok.Data;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.inventory.api.ProductDto;
import serhathar.saleservice.inventory.client.ProductFeignClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ProductFeignClient client;

    @Override
    @Transactional
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        checkInventoryExists(inventoryDto);
        Inventory inventory = toEntity(inventoryDto);
        return toDto(repository.save(inventory));
    }


    @Override
    public InventoryRepository updateInventory(String id, InventoryDto dto) {

        return null;
    }

    @Override
    public void addProductToInventory(String inventoryId, String productId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        inventory.getProductIdList().add(productId);
        repository.save(inventory);
        /*Product product = productService.getProductById(productId);
        inventory.getProductList().add(product);*/
    }

    @Override
    public void removeProductFromInventory(String inventoryId, String productId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        inventory.getProductIdList().remove(productId);
        repository.save(inventory);
    }

    private void checkInventoryExists(InventoryDto dto) {
        repository.findByName(dto.getName()).ifPresent(Inventory -> {
            throw new EntityExistsException(
                    String.format("Entity %s already exists", Inventory.getClass().getName())
            );
        });
    }

    private Inventory checkInventoryUpdate(InventoryDto dto, Inventory inventory) {
        inventory.setName(dto.getName() == null ? inventory.getName() : dto.getName());
        inventory.setName(dto.getName() == null ? inventory.getName() : dto.getName());
        return inventory;
    }

    @Override
    public List<InventoryDto> getAllInventories() {

        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public InventoryDto getById(String id) {

        return toDto(repository.getInventoryById(id));
    }

    private Inventory toEntity(InventoryDto dto) {
        Inventory inventory = new Inventory();
        List<String> productIdList = new ArrayList<>();

        for (int i = 0; i < dto.getProducList().size(); i++) {
            productIdList.add(dto.getProducList().get(i).getId());
        }

        inventory.setProductIdList(productIdList);
        inventory.setName(dto.getName());
        return inventory;
    }

    private InventoryDto toDto(Inventory inventory) {
        List<ProductDto> productList = new ArrayList<>();
        int sayi = inventory.getProductIdList().size();
        for (int i = 0; i < inventory.getProductIdList().size(); i++) {
            productList.add(client.getProductById1(inventory.getProductIdList().get(i)));
        }

        return InventoryDto.builder()
                .id(inventory.getId())
                .producList(productList)
                .name(inventory.getName())
                .build();
    }
}
