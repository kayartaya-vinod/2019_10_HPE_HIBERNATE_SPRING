package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Category;
import com.hpe.utils.HibernateUtil;

public class P02LoadCategoryById {

	public static void main(String[] args) {
		Category  c1 = null;
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		) {
			c1 = session.load(Category.class, 1);
			System.out.println("c1 refers to an object of type: " + c1.getClass().getName());
			System.out.println(c1);
		}
		
		
	}
}
