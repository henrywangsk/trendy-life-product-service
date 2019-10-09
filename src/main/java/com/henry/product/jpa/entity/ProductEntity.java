package com.henry.product.jpa.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="TBL_PRODUCTS")
public class ProductEntity {
	@Id
    @GeneratedValue
    private Long id;
    private String sku;
    private String title;
    private String description;
    private BigDecimal price;
    private int installments;
    private boolean isFreeShipping;
}
