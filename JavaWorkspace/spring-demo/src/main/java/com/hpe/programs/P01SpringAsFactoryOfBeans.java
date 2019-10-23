package com.hpe.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.cfg.AppConfig2;
import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;

public class P01SpringAsFactoryOfBeans {

	public static void main(String[] args) throws DaoException {
		// spring container that loads beans from an annotation based config class
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig2.class);
		
		ProductDao dao = ctx.getBean("jdbc", ProductDao.class);
		long pc = dao.count();
		
		System.out.println("There are " + pc + " products");
		
		ctx.close();
	}
}
