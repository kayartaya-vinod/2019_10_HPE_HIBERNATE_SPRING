package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Employee;
import com.hpe.entity.Laptop;
import com.hpe.utils.HibernateUtil;

public class P10GetLaptopAndOwner {

	public static void main(String[] args) {
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		){
			Laptop lt = session.get(Laptop.class, "HPEB0001122");
			System.out.println("Laptop : " + lt.getMake() + ", " + lt.getModel());
			System.out.println("Owned by: " + lt.getOwnedBy().getFirstName());
			
			System.out.println("-----------");
			Employee emp = session.get(Employee.class, 5);
			System.out.println("Laptop make: " + emp.getLaptop().getMake());
			System.out.println("Laptop model: " + emp.getLaptop().getModel());
			System.out.println("Laptop config: " + emp.getLaptop().getConfiguration());
		}
		
	}
}
