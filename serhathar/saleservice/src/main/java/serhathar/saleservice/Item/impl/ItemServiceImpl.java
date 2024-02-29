package serhathar.saleservice.Item.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.api.ItemService;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    ItemRepository repository;

    @Override
    public ItemDto createItem(ItemDto dto, Long amount) {
        checkItemExists(dto, amount);
        Item item = toEntity(dto);
        return toDto(repository.save(item));
    }

    @Override
    public ItemDto getItemByProductId(String id) {
        return repository.getItemByProduct(id);
    }

    private void checkItemExists(ItemDto dto, Long amount) {
        repository.findItemByProduct(dto.getProduct()).ifPresent(item -> {
            ItemDto itemDto = repository.getItemByProduct(dto.getProduct());
            itemDto.setAmount(itemDto.getAmount() + amount);
        });
    }
    public Item toEntity(ItemDto dto){
        Item item = new Item();
        item.setId(dto.getId());
        item.setProduct(dto.getProduct());
        item.setAmount(dto.getAmount());
        return item;
    }
    public ItemDto toDto(Item item){
        return ItemDto.builder()
                .product(item.getProduct())
                .id(item.getId())
                .amount(item.getAmount())
                .build();
    }
}