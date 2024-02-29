package serhathar.saleservice.Item.impl;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.api.ItemService;
import serhathar.saleservice.inventory.api.InventoryDto;
import serhathar.saleservice.inventory.impl.Inventory;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    /*@Override
    public ItemDto createItem(ItemDto dto) {
        Item item = toEntity(dto);
        return toDto(repository.save(item));
    }*/

    public ItemDto createItem1(String productId, Long amount) {
        ItemDto itemdto = new ItemDto();
        itemdto.setProductId(productId);
        itemdto.setAmount(amount);
        return toDto(repository.save(toEntity(itemdto)));
    }

    public Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setAmount(dto.getAmount());
        item.setProduct_id(dto.getProductId());
        return item;
    }

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct_id())
                .amount(item.getAmount())
                .build();
    }
}
