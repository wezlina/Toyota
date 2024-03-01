package serhathar.saleservice.Item.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {

    private String id;
    private Long amount;
    private String productId;
}