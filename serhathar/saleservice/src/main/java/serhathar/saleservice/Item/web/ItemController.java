package serhathar.saleservice.Item.web;

import serhathar.saleservice.Item.api.ItemDto;

import java.util.List;

public class ItemController {


    public List<ItemResponse> toResponse(List<ItemDto> itemDtoListDtoList) {
        return itemDtoListDtoList.stream().map(ItemResponse::toResponse).toList();
    }
}
