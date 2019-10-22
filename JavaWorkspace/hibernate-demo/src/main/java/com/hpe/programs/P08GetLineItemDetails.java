package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.LineItem;
import com.hpe.utils.HibernateUtil;

public class P08GetLineItemDetails {


	public static void main(String[] args) {
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		){
			// primary key object
			LineItem key = new LineItem(10250, 41);
			
			// entity object (persistent object), stays in the session (1st level cache)
			LineItem item = session.get(LineItem.class, key);
			
			
			System.out.println("Details of this line item: ");
			System.out.println("Product name        : " + item.getProduct().getProductName());
			System.out.println("Unit price (today)  : $" + item.getProduct().getUnitPrice());
			System.out.println("Unit price (sold at): $" + item.getUnitPrice());
			System.out.println("Employee who sold   : " + item.getOrder().getEmployee().getFirstName());
			System.out.println("Customer who bought : " + item.getOrder().getCustomer().getCompanyName());
			
		}
		
	}
	
}
