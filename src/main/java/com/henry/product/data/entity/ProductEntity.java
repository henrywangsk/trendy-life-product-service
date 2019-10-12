package com.henry.product.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "TBL_PRODUCTS")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String sku;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal price;

    private int installments;

    private boolean isFreeShipping;
}
