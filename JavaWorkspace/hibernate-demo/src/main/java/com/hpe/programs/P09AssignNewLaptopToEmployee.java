package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Employee;
import com.hpe.entity.Laptop;
import com.hpe.utils.HibernateUtil;

public class P09AssignNewLaptopToEmployee {

	public static void main(String[] args) {
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		){
			// transient object
			Laptop lt = new Laptop();
			lt.setSerialNumber("HPEB0001123");
			lt.setMake("Apple");
			lt.setModel("MacBook");
			lt.setConfiguration("128GB HDD 8GB RAM 2.5GHz processor");
			
			// persistent object
			Employee emp = session.get(Employee.class, 5);
			
			lt.setOwnedBy(emp); // association between laptop and employee
			session.persist(lt); // persistent object
			
			session.beginTransaction().commit();
			
		}
		
	}
}
