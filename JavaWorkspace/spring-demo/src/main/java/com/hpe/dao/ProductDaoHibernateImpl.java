package com.hpe.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.hpe.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("dao")
public class ProductDaoHibernateImpl implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		template.persist(product);
	}

	@Override
	public Product getProduct(Integer productId) throws DaoException {
		return template.get(Product.class, productId);
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		if (product.getUnitPrice() < 2) {
			throw new DaoException("Product price is too low!");
		}
		template.merge(product);
	}

	@Override
	public void deleteProduct(Integer productId) throws DaoException {
		Product p = this.getProduct(productId);
		if (p == null) {
			throw new DaoException("Invalid id for deletion: " + productId);
		}
		template.delete(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() throws DaoException {
		DetachedCriteria cr = DetachedCriteria.forClass(Product.class); // represents Product schema
		// we can add zero or more conditions (criterions) to filter the entities
		return (List<Product>) template.findByCriteria(cr);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException {
		return (List<Product>) template.find("from Product where unitPrice between ?0 and ?1", min, max);
	}

	@Override
	public List<Product> getProductsNotInStock() throws DaoException {
		// exapmle product
		Product exProduct = new Product();
		exProduct.setUnitsInStock(0);

		return template.findByExample(exProduct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getDiscontinuedProducts() throws DaoException {
		DetachedCriteria cr = DetachedCriteria.forClass(Product.class); // represents Product schema
		cr.add(Restrictions.eq("discontinued", 1));
		return (List<Product>) template.findByCriteria(cr);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Product> getProductsNeedOrdering() throws DaoException {
		String hql = "from Product where unitsInStock <= reorderLevel";
		return (List<Product>) template.find(hql);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Product> getProductsByCategory(String categoryName) throws DaoException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.createAlias("category", "c");
		dc.add(Restrictions.eq("c.categoryName", categoryName));
		return (List<Product>) template.findByCriteria(dc);
	}

	@SuppressWarnings("deprecation")
	@Override
	public long count() throws DaoException {
		log.info("From within ProductDaoHibernateImpl.count() method");
		return (long) template.find("select count(p) from Product p").get(0);
	}

}
