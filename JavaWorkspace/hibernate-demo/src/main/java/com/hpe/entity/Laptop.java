package com.hpe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // by default maps this class with a table named same as the class name
@NoArgsConstructor
@Getter
@Setter
public class Laptop {

	@Id
	private String serialNumber;
	private String make;
	private String model;
	private String configuration;

	@ManyToOne
	@JoinColumn(unique = true, name = "emp_id")
	private Employee ownedBy;
}
