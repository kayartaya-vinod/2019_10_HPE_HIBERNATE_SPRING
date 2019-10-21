package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hpe.entity.Category;

public class P01GetCategoryById {

	public static void main(String[] args) {
		// blank configuration object using which we can build a 
		// session factory
		Configuration cfg = new Configuration();
		
		// read from hibernate.cfg.xml
		cfg.configure();
		
		// tell Hibernate about the entity classes it has to manage
		cfg.addAnnotatedClass(Category.class);
		
		// Represents the underlying DB and a.k.a 2nd level cache
		SessionFactory factory = cfg.buildSessionFactory();
		
		// Represents a DB connection; must be closed ASAP. a.k.a 1st level cache
		Session session = factory.openSession();
		
		Category c1 = session.get(Category.class, 1);
		session.close();
		factory.close();
		
		System.out.println(c1);
	}

}
