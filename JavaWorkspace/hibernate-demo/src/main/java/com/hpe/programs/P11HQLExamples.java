package com.hpe.programs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hpe.entity.Product;
import com.hpe.entity.Shipper;
import com.hpe.utils.HibernateUtil;

public class P11HQLExamples {

	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		session = factory.openSession();

		// getAllShippers();
		// getProductsByPriceRange(50.0, 500.0); // min-> 50, max-> 500
		// getProductsByCategoryName("Condiments"); // Condiments --> categoryName
		// getProductsByPage(4); // pageNum = 4
		// getProductDetails(); // Name of product, category, supplier
		// getCategorywiseProductCount();
		increaseProductPriceBy(1.0); // incrBy =  $1.0
		
		session.close();
		factory.close();
	}

	static void increaseProductPriceBy(double incrBy) {
		String hql = "update Product set unitPrice = unitPrice + ?0";
		Transaction tx = session.beginTransaction();
		Query<?> qry = session.createQuery(hql);
		qry.setParameter(0, incrBy);
		int rc = qry.executeUpdate(); // PreparedStatement.executeUpdate()
		tx.commit();
		System.out.println("Total " + rc + " products affected!");
	}

	static void getCategorywiseProductCount() {
		// When you have a uni-directional association from Product to Category, use this:
		// String hql = "select p.category.categoryName, count(p) from Product p group by p.category.categoryName";
	
		// when you have a uni-directional association from Category to Product, use this:
		String hql = "select c.categoryName, count(p) from Category c join "
				+ "c.products as p group by c.categoryName";
		
		Query<Object[]> qry = session.createQuery(hql, Object[].class);
		qry.getResultList()
			.stream()
			.forEach(p -> System.out.printf("%-40s %-30s\n", p[0], p[1]));
	}

	static void getProductDetails() {
		String hql = "select productName, category.categoryName, supplier.companyName, unitPrice from Product";
		Query<Object[]> qry = session.createQuery(hql, Object[].class);
		qry.setMaxResults(5);
		qry.getResultList()
			.stream()
			.forEach(p -> System.out.printf(
				"%-40s %-30s %-30s %10.2f\n", 
				p[0], p[1], p[2], p[3]));
		
	}

	static void getProductsByPage(int pageNum) {
		int pageSize = 10;
		Query<Product> qry = session.createQuery("from Product", Product.class);
		qry.setMaxResults(pageSize);
		qry.setFirstResult((pageNum - 1) * pageSize);
		qry.getResultList()
			.stream()
			.forEach(p -> System.out.println(p.getProductId() + " --> " + p.getProductName()));
	}

	static void getProductsByCategoryName(String categoryName) {
		String hql = "from Product where category.categoryName = :CAT_NAME";
		Query<Product> qry = session.createQuery(hql, Product.class);
		qry.setParameter("CAT_NAME", categoryName);
		qry.getResultList()
			.stream()
			.forEach(p -> System.out.println(p.getProductName() + " --> $" + p.getUnitPrice()));
	}

	static void getProductsByPriceRange(double min, double max) {
		String hql = "from Product where unitPrice between ?0 and ?1 order by unitPrice desc";
		Query<Product> qry = session.createQuery(hql, Product.class);
		qry.setParameter(0, min);
		qry.setParameter(1, max);
		qry.getResultList()
			.stream()
			.forEach(p -> System.out.println(p.getProductName() + " --> $" + p.getUnitPrice()));
	}

	static void getAllShippers() {
		// SQL--> SELECT * FROM SHIPPERS
		// HQL--> select s1 from com.hpe.entity.Shipper s1
		// from Shipper
		Query<Shipper> qry = session.createQuery("from Shipper", Shipper.class);
		List<Shipper> list = qry.getResultList();
		list.stream().forEach(System.out::println);
	}

}
