package com.hpe.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name="order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer; // places the order
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee; // processes the order
	
	@ManyToOne
	@JoinColumn(name="ship_via")
	private Shipper shipper; // delivers the order
	
	@Column(name="order_date")
	private Date orderDate;
	@Column(name="required_date")
	private Date requiredDate;
	@Column(name="shipped_date")
	private Date shippedDate;
	
	private Double freight;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "address", column = @Column(name = "ship_address")),
		@AttributeOverride(name = "city", column = @Column(name = "ship_city")),
		@AttributeOverride(name = "region", column = @Column(name = "ship_region")),
		@AttributeOverride(name = "postalCode", column = @Column(name = "ship_postal_code")),
		@AttributeOverride(name = "country", column = @Column(name = "ship_country")),
	})
	private ContactDetails shipTo;
}







