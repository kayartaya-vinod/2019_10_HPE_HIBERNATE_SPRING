package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Supplier;
import com.hpe.utils.HibernateUtil;

public class P06GetSupplierDetails {

	public static void main(String[] args) {
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		){
			Supplier s1 = session.get(Supplier.class, 1);
			System.out.println(s1.getCompanyName());
			System.out.println(s1.getContactDetails().getCity());
			System.out.println(s1.getContactDetails().getRegion());
			System.out.println(s1.getContactDetails().getCountry());
		}
		
		
		
	}
}
