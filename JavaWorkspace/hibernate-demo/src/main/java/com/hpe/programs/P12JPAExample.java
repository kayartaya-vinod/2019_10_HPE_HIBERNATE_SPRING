package com.hpe.programs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.hpe.entity.Product;
import com.hpe.entity.Shipper;

public class P12JPAExample {

	public static void main(String[] args) {
		
		EntityManagerFactory factory;
		EntityManager em;
		
		// looks for a file META-INF/persistence.xml in the classpath
		factory = Persistence.createEntityManagerFactory("unit1");
		em = factory.createEntityManager(); // equivalent of Hibernate's session
		
		System.out.println("factory is an instanceof " + factory.getClass());
		
		// C -> em.persist
		// R -> em.find
		// U -> em.merge
		// D -> em.remove 
		Product p1 = em.find(Product.class, 1);
		System.out.println(p1.getProductName());
		
		TypedQuery<Shipper> qry = em.createQuery("select s from Shipper s", Shipper.class);
		List<Shipper> list = qry.getResultList();
		
		list.stream().forEach(System.out::println);
		
		em.close();
		factory.close();
		
	}
}
