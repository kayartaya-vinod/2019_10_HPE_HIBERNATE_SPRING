package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Category;
import com.hpe.utils.HibernateUtil;

public class P04GetAndUpdateCategoryById {

	public static void main(String[] args) {
		
		Category c1;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try(
			
			Session session = factory.openSession();
		){
			c1 = session.get(Category.class, 9);
			// c1 is a persistent object now.
			
			// hibernate marks c1 as dirty if the new name is different than the old one
			// c1.setCategoryName("Electronic products"); 
			
			// this will fire an SQL update if there is any dirty persistent objects in the session
			session.beginTransaction().commit();
		}
		
		// c1 is a detached object now!
		c1.setCategoryName("Electronic items");
		try(
			Session session = factory.openSession();
		){
			// unconditionally updates the db
			// session.update(c1);
			
			// conditional update using select+update
			session.merge(c1);
			session.beginTransaction().commit();
		}
		
	}
}







