package com.hpe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement // permission to do XML serialization
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "category" })
public class Product {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "units_in_stock")
	private Integer unitsInStock;
	@Column(name = "units_on_order")
	private Integer unitsOnOrder;
	@Column(name = "reorder_level")
	private Integer reorderLevel;
	private Integer discontinued; // for soft delete

	// association mapping
	// MANY products belong to ONE category and hence the association is ManyToOne
	// General rule of thumb: foreign key columns are always mapped with @ManyToOne
	@ManyToOne(fetch = FetchType.EAGER, cascade = {})
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
}










