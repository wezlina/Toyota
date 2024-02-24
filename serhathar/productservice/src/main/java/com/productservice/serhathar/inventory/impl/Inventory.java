package com.productservice.serhathar.inventory.impl;

import com.productservice.serhathar.product.impl.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    private static final String Table = "inventory";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_LIST = "product_list";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_NAME)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "inventory_products",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @Column(name = COL_LIST)
    private List<Product> productList;
}
