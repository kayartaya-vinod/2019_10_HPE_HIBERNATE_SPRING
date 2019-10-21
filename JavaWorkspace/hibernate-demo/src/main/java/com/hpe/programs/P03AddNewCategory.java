package com.hpe.programs;

import java.io.FileInputStream;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Category;
import com.hpe.utils.HibernateUtil;

public class P03AddNewCategory {

	public static void main(String[] args) throws IOException {
		
		String filename = "electronics.png";
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
			FileInputStream file = new FileInputStream(filename);
		){
			
			byte[] picture = new byte[file.available()];
			file.read(picture);
			
			Category c1 = new Category();
			c1.setCategoryName("Electronics");
			c1.setDescription("Electronic products such as radio tv etc");
			c1.setPicture(picture);
			
			session.persist(c1);
			// c1 now is a persistent object and has a state "new"
			// session.merge(c2) --> state == "dirty"
			// session.delete(c3) --> state == "removed"
			
			session.beginTransaction().commit();
		}
		
	}
}








