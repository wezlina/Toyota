package serhathar.saleservice.inventory.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.impl.Item;
import serhathar.saleservice.Item.impl.ItemServiceImpl;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;
import serhathar.saleservice.inventory.client.ProductFeignClient;

import java.util.ArrayList;
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
    public InventoryDto updateInventory(String id, InventoryDto dto) {

        return repository.findById(id)
                .map(inventory -> checkInventoryUpdate(dto, inventory))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addProductToInventory(String inventoryId, String productId, Long amount) {
        if (itemService.existsItemByProductId(productId)) {
            itemService.updateItemAmount(productId, amount);
        } else {
            Inventory inventory = repository.getInventoryById(inventoryId);
            inventory.getProductList().add(itemService.createItem(productId, amount));
            updateInventory(inventoryId, toDto(inventory));
        }
    }

    @Override
    @Transactional
    public void removeProductFromInventory(String inventoryId, String productId, Long amount) {
        //checkInventoryExists(toDto(repository.getInventoryById(inventoryId)));
        Inventory inventory = repository.getInventoryById(inventoryId);
        if (itemService.getItemByProductId(productId).getAmount().equals(amount)) {
            inventory.getProductList().remove(itemService.getItemByProductId(productId));
            updateInventory(inventoryId, toDto(inventory));//soft delete will be here
        } else if (itemService.getItemByProductId(productId).getAmount() < amount) {
            throw new IllegalArgumentException("number of products to be removed cannot be greater than the existing one.");
        } else {
            itemService.updateItemAmount(productId, -amount);
        }
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
        List<Item> productList = new ArrayList<>();
        Integer size = dto.getProductList().size();
        for (int i = 0; i < size; i++) {
            productList.add(itemService.toEntity(dto.getProductList().get(i)));
        }
        inventory.setProductList(productList);
        return inventory;
    }


    private InventoryDto toDto(Inventory inventory) {

        List<ItemDto> productList = new ArrayList<>();
        Integer size = inventory.getProductList().size();
        for (int i = 0; i < size; i++) {
            productList.add(itemService.toDto(inventory.getProductList().get(i)));
        }
        return InventoryDto.builder()
                .id(inventory.getId())
                .productList(productList)
                //.productList(productList)
                .name(inventory.getName())
                .build();
    }
}