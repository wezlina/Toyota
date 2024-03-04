package serhathar.saleservice.Item.impl;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Item {
    private static final String Table = "item";
    private static final String COL_ID = "id";
    private static final String COL_STATUS = "status";
    private static final String COL_PRODUCT = "productId";
    private static final String COL_AMOUNT = "amount";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = COL_ID)
    private String id;

    @Column(name =COL_STATUS)
    private Boolean status = true;

    @Column(name = COL_AMOUNT)
    private Long amount;

    @Column(name = COL_PRODUCT, unique = false)
    private String productId;//varolan urun eklenince sayi degistirme kismi hatali, eger itemi varolan bir product yeni bir sepete ekleniyorsa eklenme calismiyor.
    // item kontrol sistemi butun itemleri tarıyor onun yerine o sepette bulunan itemlerde tarama yapilmali.
}