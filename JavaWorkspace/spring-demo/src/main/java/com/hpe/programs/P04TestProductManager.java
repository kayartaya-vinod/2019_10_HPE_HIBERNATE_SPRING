package com.hpe.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.cfg.AppConfig5;
import com.hpe.dao.DaoException;
import com.hpe.service.ProductManager;

public class P04TestProductManager {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		ProductManager service = ctx.getBean(ProductManager.class);
		service.updateProducts();
		
		System.out.println("Done!");
		ctx.close();
	}
}
