package com.hpe.programs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.entity.Order;
import com.hpe.utils.HibernateUtil;

public class P07GetOrderData {


	public static void main(String[] args) {
		
		try(
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.openSession();
		){
			Integer orderId = 10250;
			Order ord = session.get(Order.class, orderId);
			System.out.println("Order date: " + ord.getOrderDate());
			System.out.println("Required date: " + ord.getRequiredDate());
			System.out.println("Shipped date: " + ord.getShippedDate());
			System.out.println("Freight: $" + ord.getFreight());
			
			System.out.println("Customer: " + ord.getCustomer());
			System.out.println("Employee: " + ord.getEmployee().getFirstName());
			System.out.println("Manager: " + ord.getEmployee().getManager().getFirstName());
			
			System.out.println("Shipping to: " + ord.getShipTo().getCity());
		}
		
	}
}
