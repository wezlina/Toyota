package serhathar.saleservice.Item.impl;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {

   Optional<Object> findItemById(String id);
}
