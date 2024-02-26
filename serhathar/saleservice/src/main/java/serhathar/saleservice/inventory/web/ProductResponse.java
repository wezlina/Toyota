package serhathar.saleservice.inventory.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.inventory.api.CategoryDto;
import serhathar.saleservice.inventory.api.ProductDto;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String barcode;
    private String name;
    private String brand;
    private String description;
    private String categoryName;
    private Date creaDate;
    private Boolean status;
    private BigDecimal price;
    private CategoryDto category;

    public static ProductResponse toResponse(ProductDto dto) {
        return ProductResponse.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .barcode(dto.getBarcode())
                .creaDate(dto.getCreaDate())
                .name(dto.getName())
                .brand(dto.getBrand())
                .categoryName(dto.getCategoryName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .build();
    }
}