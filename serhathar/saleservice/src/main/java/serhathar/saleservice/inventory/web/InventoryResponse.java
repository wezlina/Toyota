package serhathar.saleservice.inventory.web;

import serhathar.saleservice.inventory.api.InventoryDto;
import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.inventory.api.ProductDto;
import serhathar.saleservice.inventory.client.ProductFeignClient;

import java.util.List;

@Data
@Builder
public class InventoryResponse {

    private String id;
    private String name;
    private List<ProductDto> productList;

    public static InventoryResponse toResponse(InventoryDto dto) {
        return InventoryResponse.builder()
                .id(dto.getId())
                .productList(dto.getProducList())
                .name(dto.getName())
                .build();
    }
}
