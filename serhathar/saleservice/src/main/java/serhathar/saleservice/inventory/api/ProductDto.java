package serhathar.saleservice.inventory.api;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

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
}