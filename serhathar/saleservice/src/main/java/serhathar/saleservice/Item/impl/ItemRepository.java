package serhathar.saleservice.Item.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import serhathar.saleservice.Item.api.ItemDto;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Object> findItemByProduct(String id);

    ItemDto getItemByProduct(String id);
}
