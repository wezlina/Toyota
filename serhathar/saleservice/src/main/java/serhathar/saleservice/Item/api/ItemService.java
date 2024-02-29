package serhathar.saleservice.Item.api;


public interface ItemService {
    ItemDto createItem(ItemDto dto, Long amount);

    ItemDto getItemByProductId(String id);
}
