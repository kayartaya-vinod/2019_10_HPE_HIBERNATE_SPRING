package com.hpe.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// A class is qualified as component, if it is annotated with one of:
// @Component, @Service, @Repository, @Controller, @RestController, @Configuration
@Configuration
@PropertySource("classpath:jdbc-info.properties")
@ComponentScan(basePackages = { "com.hpe.dao" })
public class AppConfig4 {

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

}
