package serhathar.saleservice.Item.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {
    private static final String Table = "item";
    private static final String COL_ID = "id";
    private static final String COL_AMOUNT = "id";
    private static final String COL_PRODUCT = "id";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_PRODUCT)
    private String product;

    @Column(name = COL_AMOUNT)
    private Long amount;
}
