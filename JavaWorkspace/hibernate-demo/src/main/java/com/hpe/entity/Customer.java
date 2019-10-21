package com.hpe.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	@Id
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "contact_title")
	private String contactTitle;
	@Embedded
	private ContactDetails contactDetails;
	private String phone;
	private String fax;
}
