package com.hpe.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.hpe.entity.Category;
import com.hpe.entity.Product;
import com.hpe.entity.Supplier;

@Configuration
@PropertySource("classpath:jdbc-info.properties")
public class AppConfig5 {

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
	@Bean 
	public DataSource h2Ds() {
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
	
	@Bean 
	public DataSource mysqlDs() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost/northwind");
		bds.setUsername("root");
		bds.setPassword("Welcome#123");
		return bds;
	}

	// register a bean called "sessionFactory" of type
	// org.hibernate.SessionFactory with spring container
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean factory(@Qualifier("h2Ds") DataSource ds) { // dependency injection
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(ds); // manual wiring
		bean.setAnnotatedClasses(Product.class, Category.class, Supplier.class);

		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		bean.setHibernateProperties(props);

		// instead, read all the information from hibernate.cfg.xml file:
		// bean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return bean;
		// Spring calls bean.getObject() whenever there is need of a
		// SessionFactory instance.
	}

	// register a bean called "ht" of type
	// org.springframework.orm.hibernate5.HibernateTemplate
	// with spring container
	@Bean(name = "ht")
	public HibernateTemplate template(SessionFactory factory) {
		return new HibernateTemplate(factory);
	}

}






