package com.hpe.dao;

import org.springframework.data.repository.CrudRepository;

import com.hpe.entity.Customer;

public interface CustomerDao extends CrudRepository<Customer, String> {
	
	public Customer findByPhone(String phone);
}
