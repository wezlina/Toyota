package serhathar.saleservice.Item.api;

import serhathar.saleservice.Item.impl.Item;

public interface ItemService {


    //ItemDto createItem(ItemDto dto);

    Boolean existsItemByProductId(String productId);

    Item getItemByProductId(String id);

    void updateItemAmount(String productId, Long amount);

    Item createItem(String productId, Long amount);


    /*static List<ItemDto> toDto(Item item, ProductDto dto){
        List<ItemDto> list = new ArrayList<>();
        ItemDto itemDto = new ItemDto(item.getId(), item.getAmount(), dto);
        list.add(itemDto);
        return list;
    }
    static List<Item> toEntity(ItemDto itemDto, ProductDto dto){
        List<Item> list = new ArrayList<>();
        Item item = new Item(itemDto.getId(), itemDto.getAmount(), dto.getId());
        list.add(item);
        return list;
    }*/
}