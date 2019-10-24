package com.hpe.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.hpe.dao.LocalProductDaoBean;
import com.hpe.dao.ProductDao;
import com.hpe.entity.Category;
import com.hpe.entity.Product;
import com.hpe.entity.Supplier;
import com.hpe.service.ProductManager;

@PropertySource("classpath:jdbc-info.properties")
@Configuration
public class AppConfig5a {
	@Value("${jdbc.driver_class_name}")
	private String driver;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource h2Ds() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(this.driver);
		bds.setUrl(this.url);
		bds.setUsername(this.username);
		bds.setPassword(this.password);

		return bds;
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean factory(DataSource ds) { // dependency injection
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(ds); // manual wiring
		bean.setAnnotatedClasses(Product.class, Category.class, Supplier.class);

		Properties props = new Properties();
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		bean.setHibernateProperties(props);

		return bean;
	}

	@Bean(name = "ht")
	public HibernateTemplate template(SessionFactory factory) {
		return new HibernateTemplate(factory);
	}

	@Bean(name = "dao")
	public LocalProductDaoBean productDao(HibernateTemplate ht) {
		return new LocalProductDaoBean(ht);
	}
	
	@Bean
	public ProductManager mgr(ProductDao dao) {
		return new ProductManager(dao);
	}
}
