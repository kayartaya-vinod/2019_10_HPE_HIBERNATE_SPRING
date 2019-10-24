package com.hpe.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// This is not an ENTITY class.
// But the fields relate to the columns of CUSTOMERS, EMPMLOYEES, ORDERS, SUPPLIERS table
// Will be used in the corresponding classes.
@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContactDetails {
	private String address;
	private String city;
	private String region;
	@Column(name = "postal_code")
	private String postalCode;
	private String country;
}
