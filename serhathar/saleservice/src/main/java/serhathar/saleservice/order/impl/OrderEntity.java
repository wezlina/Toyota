package serhathar.saleservice.order.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {
    private static final String COL_ID = "id";
    private static final String COL_INVENTORY = "inventoryId";
    private static final String COL_PRICE = "total_price";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_INVENTORY)
    private String inventoryId;

    @Column(name = COL_PRICE)
    private BigDecimal totalPrice;

    //@Column(name = COL_USER)
    //private String userId;

}
