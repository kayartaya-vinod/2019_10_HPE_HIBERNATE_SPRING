package com.hpe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "products", "picture" })
public class Category {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "category_name")
	private String categoryName;
	private String description;
	private byte[] picture;

	// Association Mapping - ONE category has MANY products
	@OneToMany(mappedBy = "category") /// category is the field in Product.java
	// @JoinColumn(name = "category_id") // foreign key in PRODUCTS table
	private List<Product> products;
}










