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
import serhathar.saleservice.inventory.api.ProductDto;
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
    public List<ItemDto> findItemsByInventoryId(String inventoryId){

        return getById(inventoryId).getProductList();
    }

    @Override
    @Transactional
    public void deleteInventory(String inventoryId) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        inventory.setStatus(false);
        updateInventory(inventoryId, toDto(inventory));
    }

    public ItemDto findItemInInventoryByProduct(List<ItemDto> itemList, ProductDto product){
        if (itemList != null) {
            for (ItemDto dto : itemList) {
                if (dto.getProduct().equals(product)) {
                    return dto;
                }
            }
        }
        else {
            throw new NullPointerException("The itemList is returned a null value");
        }
        return null;
    }

    public Boolean checkItemsForProductExists(List<ItemDto> itemList, ProductDto product){

        if (itemList != null) {
            for (ItemDto dto : itemList) {
                Boolean a = dto.getProduct().equals(product);
                //Boolean b = dto.getProduct() == product;
                if (a) {
                    //return b;
                    return a;
                }
            }
        }
        else {
            throw new NullPointerException("The itemList is returned a null value");
        }
        return false;
    }

    @Override
    @Transactional
    public InventoryDto updateInventory(String id, InventoryDto dto) {

        return repository.findById(id)
                .map(inventory -> checkInventoryUpdate(dto, inventory))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void addProductToInventory(String inventoryId, String productId, Long amount) {
        if (checkItemsForProductExists(findItemsByInventoryId(inventoryId), client.getProductById1(productId))) {
            //itemService.updateItemAmount(productId, amount);//burda itemi productId ile cagirıyoruz o yuzden hata oluyor.
            ItemDto dto = findItemInInventoryByProduct(findItemsByInventoryId(inventoryId), client.getProductById1(productId));
            dto.setAmount(dto.getAmount()+ amount);
            itemService.updateItemAmount(dto.getId(), amount);
            //itemService.updateItemAmount();
        }
        else {
            Inventory inventory = repository.getInventoryById(inventoryId);
            inventory.getProductList().add(itemService.createItem(productId, amount));
            updateInventory(inventoryId, toDto(inventory));
        }
    }

    @Override
    @Transactional
    public void removeProductFromInventory(String inventoryId, String productId, Long amount) {
        Inventory inventory = repository.getInventoryById(inventoryId);
        ItemDto item = findItemInInventoryByProduct(toDto(inventory).getProductList(), client.getProductById1(productId));

        if(checkItemsForProductExists(toDto(inventory).getProductList(), client.getProductById1(productId))){

            if(item.getAmount().equals(amount)){
                inventory.getProductList().remove(itemService.toEntity(item));
                itemService.deleteItem(item.getId());
                updateInventory(inventoryId, toDto(inventory));
                itemService.updateItemAmount(item.getId(), -amount);
            }
            else if(item.getAmount() > amount){
                itemService.updateItemAmount(item.getId(), -amount);
            }
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
        //inventory.setProductList(dto.getProductList() == null ? inventory.getProductList() : dto.getProductList());
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

        if(dto.getProductList() != null){
            int size = dto.getProductList().size();
            for (int i = 0; i < size; i++) {
                productList.add(itemService.toEntity(dto.getProductList().get(i)));
            }
        }
        inventory.setName(dto.getName());
        inventory.setProductList(productList);
        return inventory;
    }


    private InventoryDto toDto(Inventory inventory) {
        List<ItemDto> productList = new ArrayList<>();

        if (inventory.getProductList() != null){
            int size = inventory.getProductList().size();
            for (int i = 0; i < size; i++) {
                productList.add(itemService.toDto(inventory.getProductList().get(i)));
            }
        }
        return InventoryDto.builder()
                .id(inventory.getId())
                .status(inventory.getStatus())
                .productList(productList)
                .name(inventory.getName())
                .build();
    }
}