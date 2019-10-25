package com.hpe.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
