package com.hpe.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.hpe.dao.ProductDao;
import com.hpe.dao.ProductDaoJdbcImpl;

@Configuration
@PropertySource("classpath:jdbc-info.properties")
public class AppConfig3 {

	@Value("${jdbc.driver_class_name}")
	private String driver;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	// registering a new bean of type DataSource named as "ds"/"dataSource", with
	// Spring container
	@Bean(name = { "ds", "dataSource" })
	public DataSource apacheDs() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(this.driver);
		bds.setUrl(this.url);
		bds.setUsername(this.username);
		bds.setPassword(this.password);
		
		bds.setInitialSize(50);
		bds.setMaxTotal(100);
		bds.setMaxWaitMillis(500);
		bds.setMaxIdle(50);
		bds.setMinIdle(10);
		
		return bds;
	}

	// registering a new bean of type ProductDao named as "jdbc", with 
	// Spring container
	@Bean
	public ProductDao jdbc(DataSource ds) { // dependency injection
		ProductDaoJdbcImpl dao = new ProductDaoJdbcImpl();
		//dao.setDataSource(ds); // manual wiring
		return dao;
	}
}







