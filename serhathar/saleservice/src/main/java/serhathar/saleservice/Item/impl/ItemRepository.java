package serhathar.saleservice.Item.impl;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {

   Boolean existsItemByProductId(String productId);

   Item getItemByProductId(String id);
}