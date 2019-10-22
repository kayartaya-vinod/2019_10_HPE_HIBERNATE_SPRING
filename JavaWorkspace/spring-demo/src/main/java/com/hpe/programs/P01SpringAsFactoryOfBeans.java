package com.hpe.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.cfg.AppConfig1;
import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;

public class P01SpringAsFactoryOfBeans {

	public static void main(String[] args) throws DaoException {
		// spring container that loads beans from an annotation based config class
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
		System.out.println("-------------pass 1");
		
		
		ProductDao dao = ctx.getBean("dummy", ProductDao.class);
		// ProductDao dao2 = ctx.getBean("dummy", ProductDao.class);
		
		// System.out.println("dao==dao2 is " + (dao==dao2));
		long pc = dao.count();
		System.out.println("There are " + pc + " products");

		ctx.close();
	}
}
