package com.hpe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@Getter
@Setter
public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "order_id")
	private Integer orderId;
	@Id
	@Column(name = "product_id")
	private Integer productId;

	public LineItem(Integer orderId, Integer productId) {
		this.orderId = orderId;
		this.productId = productId;
	}

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	@Column(name = "unit_price")
	private Double unitPrice;
	private Integer quantity;
	private Double discount;
}
