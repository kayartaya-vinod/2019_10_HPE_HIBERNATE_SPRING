package com.hpe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shippers")
@NoArgsConstructor
@Getter
@Setter
public class Shipper {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "shipper_id")
	private Integer shipperId;
	@Column(name = "company_name")
	private String companyName;
	private String phone;
}
