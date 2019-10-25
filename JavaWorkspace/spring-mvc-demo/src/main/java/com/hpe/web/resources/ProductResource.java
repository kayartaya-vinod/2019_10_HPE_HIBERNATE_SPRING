package com.hpe.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;
import com.hpe.entity.Product;

@RequestMapping("/api/products")
@RestController
public class ProductResource {

	@Autowired
	ProductDao dao;

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Integer id) throws DaoException {
		return dao.getProduct(id);
	}

}
