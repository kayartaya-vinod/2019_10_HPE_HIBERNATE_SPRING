package com.hpe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	@Embedded
	private ContactDetails contactDetails;

	@ManyToOne
	@JoinColumn(name = "reports_to")
	private Employee manager;

	@OneToMany(mappedBy = "manager")
	private List<Employee> subordinates;
	
	// ownedBy is a field in class Laptop where @JoinColumn is defined
	@OneToOne(mappedBy = "ownedBy") 
	private Laptop laptop;
}







