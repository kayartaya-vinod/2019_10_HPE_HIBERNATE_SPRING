package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Product;
import com.hpe.utils.HibernateUtil;

public class P05GetProductAndCategory {

	public static void main(String[] args) {
		Product p1;

		try (
				SessionFactory factory = HibernateUtil.getSessionFactory(); 
				Session session = factory.openSession();
		) {
			p1 = session.get(Product.class, 18);
		}

//		System.out.println(p1);
		
		
		System.out.println("Name       = " + p1.getProductName());
		System.out.println("Unit price = $" + p1.getUnitPrice());
		System.out.println("Category   = " + p1.getCategory().getCategoryName());
	}

}
