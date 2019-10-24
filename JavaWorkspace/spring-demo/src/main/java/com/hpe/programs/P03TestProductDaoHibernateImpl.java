package com.hpe.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hpe.cfg.AppConfig5;
import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;
import com.hpe.entity.Product;

public class P03TestProductDaoHibernateImpl {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig5.class);
		ProductDao dao = ctx.getBean("dao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass().getName());

		List<Product> list = dao.getAllProducts();
		System.out.println("There are " + list.size() + " products.");

		Double min = 50.0, max = 100.0;
		list = dao.getProductsByPriceRange(min, max);
		System.out.printf("There are %d products between $%.1f and $%.1f\n", list.size(), min, max);

		min = 100.0;
		max = 50.0;
		list = dao.getProductsByPriceRange(min, max);
		System.out.printf("There are %d products between $%.1f and $%.1f\n", list.size(), min, max);

		list = dao.getProductsByCategory("Beverages");
		System.out.printf("There are %d beverages\n", list.size());

		
		list = dao.getDiscontinuedProducts();
		System.out.printf("There are %d discontinued products\n", list.size());

		list = dao.getProductsNotInStock();
		System.out.printf("There are %d products not in stock\n", list.size());

		long pc = dao.count();
		System.out.printf("There are %d products\n", pc);

		Product p = dao.getProduct(11);
		System.out.println("Product name = " + p.getProductName());
		System.out.println("Price        = $" + p.getUnitPrice());

//		p.setUnitPrice(p.getUnitPrice() + 1);
//		dao.updateProduct(p);
//		
//		System.out.println("After updating...");
//		System.out.println("Product name = " + p.getProductName());
//		System.out.println("Price        = $" + p.getUnitPrice());
				
		ctx.close();
	}
}
