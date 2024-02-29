package serhathar.saleservice.inventory.impl;

import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.impl.Item;
import serhathar.saleservice.Item.impl.ItemServiceImpl;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.inventory.client.ProductFeignClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ItemServiceImpl itemService;
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
    public void addProductToInventory(String inventoryId, String productId, Long amount) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        Item item = itemService.toEntity(itemService.createItem1(productId, amount));
        inventory.getProductList().add(item);
        repository.save(inventory);
    }

    @Override
    @Transactional
    public void removeProductFromInventory(String inventoryId, String productId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        inventory.getProductList().remove(productId);
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
        List<Item> productList = dto.getProductList();

        inventory.setProductList(productList);
        inventory.setName(dto.getName());
        return inventory;
    }

    private InventoryDto toDto(Inventory inventory) {
        List<Item> productList = inventory.getProductList();


        return InventoryDto.builder()
                .id(inventory.getId())
                .productList(productList)
                .name(inventory.getName())
                .build();
    }
}/*if (dto.getProductDtoList() == null) {
            size = 0;
        }
        else {
            size = dto.getProductDtoList().size();
        }

        for (int i = 0; i < size; i++) {
            productIdList.add(dto.getProductDtoList().get(i).getId());
        }*/
        /*if (inventory.getProductIdList() == null) {
            size = 0;
        }
        else {
            size = inventory.getProductIdList().size();
        }

        for (int i = 0; i < size; i++) {
            productList.add(client.getProductById1(inventory.getProductIdList().get(i)));
        }*/