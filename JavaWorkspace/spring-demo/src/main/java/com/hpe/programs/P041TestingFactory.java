package com.hpe.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.cfg.AppConfig5a;
import com.hpe.service.ProductManager;

public class P041TestingFactory {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5a.class);
		
		ProductManager mgr = ctx.getBean(ProductManager.class);
		System.out.println("dao is an instanceof " + mgr.getClass());
		
		ctx.close();
	}
}
