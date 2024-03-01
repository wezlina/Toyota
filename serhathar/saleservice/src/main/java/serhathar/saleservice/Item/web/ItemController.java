package serhathar.saleservice.Item.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serhathar.saleservice.Item.impl.ItemServiceImpl;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/sale-service/items")
public class ItemController {//only open for admin

    private final ItemServiceImpl service;
    /*@PostMapping(path = "/add")
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest request) {
        ItemDto dto = service.createItem(request.toDto());
        return ResponseEntity.ok(ItemResponse.toResponse(dto));
    }*/
}