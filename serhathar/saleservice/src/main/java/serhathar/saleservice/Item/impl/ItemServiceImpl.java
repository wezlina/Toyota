package serhathar.saleservice.Item.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.api.ItemService;
import serhathar.saleservice.inventory.client.ProductFeignClient;


@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ProductFeignClient client;

    @Override
    public Boolean existsItemByProductId(String productId) {
        return repository.existsItemByProductId(productId);
    }

    @Override
    public Item getItemByProductId(String id) {
        return repository.getItemByProductId(id);
    }
    /* repository.findById(id)
                .map(product -> checkProductUpdate(dto, product))
                .map(repository::save)*/

    @Override
    @Transactional
    public void updateItemAmount(String id, Long amount) {
        //repository.getItemByProductId(productId).setAmount(repository.getItemByProductId(productId).getAmount() + amount);
        //repository.save(getItemByProductId(productId));
       Item dto = repository.getById(id);
       dto.setAmount(dto.getAmount() + amount);
       repository.save(dto);
    }

    public void deleteItemByStatus(String itemId) {
        Item dto = repository.getById(itemId);
        dto.setStatus(false);
        repository.save(dto);
    }

    @Override
    @Transactional
    public Item createItem(String productId, Long amount) {
        Item item = new Item();
        item.setProductId(productId);
        item.setAmount(amount);
        return repository.save(item);
    }

    public Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setAmount(dto.getAmount());
        item.setProductId(dto.getProduct().getId());
        return item;
    }

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .status(item.getStatus())
                .product(client.getProductById1(item.getProductId()))
                .amount(item.getAmount())
                .build();
    }
}