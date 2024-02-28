package com.productservice.serhathar.product.impl;

import com.productservice.serhathar.category.impl.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product implements Serializable {
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_CREATE_DATE = "create_date";
    private static final String COL_BRAND = "brand";
    private static final String COL_BARCODE = "barcode";
    private static final String COL_QUANTITY = "quantity";
    private static final String COL_PRICE = "price";
    private static final String COL_CATEGORY_ID = "category_id";

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = COL_ID)
    private String id;

    @Column(name = COL_BARCODE)
    private String barcode;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = COL_CREATE_DATE)
    private Date creaDate;

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_BRAND)
    private String brand;

    @Column(name = COL_PRICE, nullable = false)
    private BigDecimal price;

    @Column(name = COL_QUANTITY, nullable = false)
    private Long amount;

    @Column
    private Boolean status = true;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = COL_CATEGORY_ID)
    Category category;
}