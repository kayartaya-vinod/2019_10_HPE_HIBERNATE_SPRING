package com.hpe.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.hpe.dao.ProductDao;
import com.hpe.dao.ProductDaoDummyImpl;
import com.hpe.dao.ProductDaoJdbcImpl;

// Spring uses this class to load beans (objects) into 
// the spring container.
@Configuration
public class AppConfig1 {
	
	public AppConfig1() {
		System.out.println("AppConfig1 instantiated!");
	}
	
	@Lazy	// effective only for singleton scoped bean
	@Bean
	public ProductDao jdbc() {
		System.out.println("AppConfig1.jdbc() called! this is an instanceof " + this.getClass());
		return new ProductDaoJdbcImpl();
	}

	@Scope("prototype")
	@Bean
	public ProductDao dummy() {
		for(int i=0; i<10; i++) {
			System.out.println("Inside loop, i is: " + i);
			jdbc();
		}
		System.out.println("AppConfig1.dummy() called!");
		return new ProductDaoDummyImpl();
	}
}
