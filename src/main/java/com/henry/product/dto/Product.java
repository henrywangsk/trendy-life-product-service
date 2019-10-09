package com.henry.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "product")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 8177242098808853897L;
	
    @XmlAttribute(name = "id")
    @Getter @Setter private Long id;
  
    @XmlAttribute(name="sku")
    @Getter @Setter private String sku;
  
    @XmlElement(name = "title")
    @Getter @Setter private String title;
  
    @XmlElement(name = "description")
    @Getter @Setter private String description;
    
    @XmlAttribute(name = "price")
    @Getter @Setter private BigDecimal price;
  
    @XmlAttribute(name="installments")
    @Getter @Setter private int installments;
  
    
    @XmlElement(name = "isFreeShipping")
    @Getter @Setter private boolean isFreeShipping;
}
