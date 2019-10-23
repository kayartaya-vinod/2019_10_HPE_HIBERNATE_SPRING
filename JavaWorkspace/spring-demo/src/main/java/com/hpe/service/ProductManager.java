package com.hpe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;
import com.hpe.entity.Product;

@Service
public class ProductManager {

	@Autowired
	private ProductDao dao;

	@Transactional(rollbackFor = DaoException.class)
	public void updateProducts() throws DaoException {
		Product p1 = dao.getProduct(1);
		Product p2 = dao.getProduct(2);
		Product p3 = dao.getProduct(3);

		p1.setUnitPrice(p1.getUnitPrice() - 5);
		p2.setUnitPrice(p2.getUnitPrice() - 5);
		p3.setUnitPrice(p3.getUnitPrice() - 5);

		dao.updateProduct(p1);
		dao.updateProduct(p2);
		dao.updateProduct(p3);

	}
}
