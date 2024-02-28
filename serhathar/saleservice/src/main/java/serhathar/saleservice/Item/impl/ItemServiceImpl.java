package serhathar.saleservice.Item.impl;

import jakarta.persistence.EntityExistsException;
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

        return null;
    }

    private void checkItemExists(ItemDto dto, Long amount) {
        repository.findItemById(dto.getProduct()).ifPresent(item -> {
            repository.findItemById(dto.getId());
                    //eger bu product id ile cart yaratılmışsa varolana amountu eklenecek


                    /*.map(product -> checkProductUpdate(dto, product))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(EntityNotFoundException::new);*/
        });
    }
}
