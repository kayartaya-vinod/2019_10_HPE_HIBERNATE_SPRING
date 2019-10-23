package com.hpe.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.hpe.dao.ProductDao;
import com.hpe.dao.ProductDaoJdbcImpl;

@Configuration
@PropertySource("classpath:jdbc-info.properties")
public class AppConfig2 {
	
	// For the class AppConfig2, the members driver, url, username, password
	// are dependencies. Spring injects these dependencies via the 
	// annotation @Value. For this it reads key/value pairs from 
	// a property source using @PropertySource
	
	@Value("${jdbc.driver_class_name}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public ProductDao jdbc() {
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		// setting of dependencies 
		dao.setDriver(this.driver);
		dao.setUrl(this.url);
		dao.setUsername(this.username);
		dao.setPassword(this.password);
		return dao;
	}
}
