package serhathar.saleservice.inventory.web;

import lombok.Builder;
import lombok.Data;
import serhathar.saleservice.inventory.api.CategoryDto;
import serhathar.saleservice.inventory.api.ProductDto;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private final String name;
    private final String brand;
    private final BigDecimal price;
    private final CategoryDto category;
    private final String categoryName;
    private final String description;
    private final String barcode;
    private final Boolean status;

    public ProductDto toDto() {
        return ProductDto.builder()
                .name(name)
                .brand(brand)
                .status(status)
                .description(description)
                .price(price)
                .category(category)
                .categoryName(categoryName)
                .barcode(barcode)
                .build();
    }
}