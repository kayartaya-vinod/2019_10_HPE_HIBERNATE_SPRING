package com.hpe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shippers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Shipper {
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "shipper_id")
	private Integer shipperId;
	@Column(name = "company_name")
	private String companyName;
	private String phone;
}
