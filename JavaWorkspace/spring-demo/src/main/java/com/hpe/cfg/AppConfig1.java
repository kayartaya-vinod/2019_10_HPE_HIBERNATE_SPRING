package com.hpe.cfg;

import org.springframework.context.annotation.Bean;

import com.hpe.dao.ProductDao;
import com.hpe.dao.ProductDaoDummyImpl;
import com.hpe.dao.ProductDaoJdbcImpl;

// Spring uses this class to load beans (objects) into 
// the spring container.
public class AppConfig1 {
	
	public AppConfig1() {
		System.out.println("AppConfig1 instantiated!");
	}

	@Bean
	public ProductDao jdbc() {
		System.out.println("AppConfig1.jdbc() called!");
		return new ProductDaoJdbcImpl();
	}

	@Bean
	public ProductDao dummy() {
		System.out.println("AppConfig1.dummy() called!");
		return new ProductDaoDummyImpl();
	}
}
