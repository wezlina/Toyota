package serhathar.saleservice.Item.api;

import serhathar.saleservice.Item.impl.Item;

public interface ItemService {


    //ItemDto createItem(ItemDto dto);

    Boolean existsItemByProductId(String productId);

    Item getItemByProductId(String id);

    void updateItemAmount(String productId, Long amount);

    Item createItem(String productId, Long amount);
}