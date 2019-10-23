package com.hpe.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.entity.Product;

@Transactional(readOnly = true, rollbackFor = DaoException.class)
public interface ProductDao {

	// CRUD OPERATIONS
	
	@Transactional(readOnly = false)
	public default void addProduct(Product product) throws DaoException{
		throw new DaoException("Not implemented yet!");
	}
	
	public default Product getProduct(Integer productId) throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public default void updateProduct(Product product) throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	@Transactional(readOnly = false)
	public default void deleteProduct(Integer productId) throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	// QUERIES
	
	public default List<Product> getAllProducts()  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	public default List<Product> getProductsByPriceRange(Double min, Double max)  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	public default List<Product> getProductsNotInStock()  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	public default List<Product> getDiscontinuedProducts()  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	public default List<Product> getProductsNeedOrdering()  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}
	
	public default List<Product> getProductsByCategory(String categoryName)  throws DaoException {
		throw new DaoException("Not implemented yet!");
	}

	public long count() throws DaoException;
	
}




