package serhathar.saleservice.Item.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serhathar.saleservice.Item.api.ItemDto;
import serhathar.saleservice.Item.impl.ItemServiceImpl;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/sale-service/items")
public class ItemController {

    private final ItemServiceImpl service;
    /*@PostMapping(path = "/add")
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest request) {
        ItemDto dto = service.createItem(request.toDto());
        return ResponseEntity.ok(ItemResponse.toResponse(dto));
    }*/
}
