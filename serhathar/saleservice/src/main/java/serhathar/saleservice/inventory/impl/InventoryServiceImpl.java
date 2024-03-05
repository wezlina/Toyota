package serhathar.saleservice.inventory.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.impl.ItemServiceImpl;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.api.InventoryService;
import serhathar.saleservice.inventory.api.ProductDto;
import serhathar.saleservice.inventory.client.ProductFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ItemDto> findItemsByInventoryId(String inventoryId) {

        return getById(inventoryId).getProductList();
    }

    @Override
    @Transactional
    public void deleteInventory(String inventoryId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        inventory.setStatus(false);
        updateInventory(inventoryId, toDto(inventory));
    }

    public ItemDto findItemInInventoryByProduct(List<ItemDto> itemList, ProductDto product) {
        if (itemList != null) {
            for (ItemDto dto : itemList) {
                if (dto.getProduct().equals(product)) {
                    return dto;
                }
            }
        } else {
            throw new NullPointerException("The itemList is returned a null value");
        }
        return null;
    }

    public Boolean checkItemsForProductExists(List<ItemDto> itemList, ProductDto product) {

        if (itemList != null) {
            for (ItemDto dto : itemList) {
                Boolean a = dto.getProduct().equals(product);
                //Boolean b = dto.getProduct() == product;
                if (a) {
                    //return b;
                    return a;
                }
            }
        } else {
            throw new NullPointerException("The itemList is returned a null value");
        }
        return false;
    }

    @Override
    @Transactional
    public InventoryDto updateInventory(String id, InventoryDto dto) {

        return repository.findById(id)
                //.map(inventory -> checkInventoryUpdate(dto, inventory))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public InventoryDto addProductToInventory(String inventoryId, String productId, Long amount) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        List<ItemDto> itemDtoList = findItemsByInventoryId(inventoryId);
        ProductDto productDto = client.getProductById1(productId);

        if (checkItemsForProductExists(itemDtoList, productDto)) {
            ItemDto dto = findItemInInventoryByProduct(itemDtoList, productDto);
            dto.setAmount(dto.getAmount() + amount);
            itemService.updateItemAmount(dto.getId(), amount);
        }
        else {
            inventory.getProductList().add(itemService.createItem(productId, amount));
            updateInventory(inventoryId, toDto(inventory));
        }
        return toDto(inventory);
    }

    @Override
    @Transactional
    public InventoryDto removeProductFromInventory(String inventoryId, String productId, Long amount) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        ProductDto productDto = client.getProductById1(productId);
        ItemDto itemDto = findItemInInventoryByProduct(toDto(inventory).getProductList(), productDto);

        if (checkItemsForProductExists(toDto(inventory).getProductList(), productDto)) {
            if (itemDto.getAmount().equals(amount)) {
                //inventory.getProductList().remove(itemService.toEntity(itemDto)); disabled for soft delete
                itemService.deleteItemByStatus(itemDto.getId());
                itemService.updateItemAmount(itemDto.getId(), -amount);
                updateInventory(inventoryId, toDto(inventory));
            }
            else if (itemDto.getAmount() > amount) {
                itemService.updateItemAmount(itemDto.getId(), -amount);
            }
        }
        return toDto(inventory);
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
        //inventory.setProductList(dto.getProductList() == null ? inventory.getProductList() : itemService.toEntityList(dto.getProductList()));
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
        inventory.setName(dto.getName());
        if(dto.getProductList() != null){
            inventory.setProductList(
                    dto.getProductList()
                            .stream()
                            .map(itemService::toEntity)
                            .collect(Collectors.toList()));
        }
        return inventory;
    }

    private InventoryDto toDto(Inventory inventory) {
        List<ItemDto> productList = new ArrayList<>();
        if (inventory.getProductList() != null){
             productList = inventory.getProductList()
                    .stream()
                    .map(itemService::toDto)
                    .toList();
        }

        return InventoryDto.builder()
                .id(inventory.getId())
                .status(inventory.getStatus())
                .productList(productList)
                .name(inventory.getName())
                .build();
    }
}