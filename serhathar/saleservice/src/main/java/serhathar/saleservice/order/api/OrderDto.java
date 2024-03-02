package serhathar.saleservice.order.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OrderDto {

    //String userId;//Her order inventory'nin ait oldugu kullanici ve inventory'nin id'sini tutar.
    String id;
    String inventoryId;
    BigDecimal totalPrice;
}
