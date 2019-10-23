package com.hpe.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.hpe.cfg.AppConfig5;
import com.hpe.entity.Product;

public class P02HibernateTemplateExamples {

	static HibernateTemplate ht;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		ht = ctx.getBean(HibernateTemplate.class);
		
		getProductById(45); // productId = 45;

		ctx.close();
	}

	static void getProductById(int id) {
		Product p1 = ht.get(Product.class, id);
		System.out.println("Name = " + p1.getProductName());
		System.out.println("Category = " + p1.getCategory().getCategoryName());
		System.out.println("Supplier = " + p1.getSupplier().getCompanyName());
	}
}
