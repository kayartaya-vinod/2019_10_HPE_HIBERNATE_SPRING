package com.hpe.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hpe.dao.DaoException;
import com.hpe.dao.ProductDao;

@Controller // qualifies for component scan + scanned by HandlerMapping
public class ProductController {
	
	@Autowired
	ProductDao dao;
	
	// @RequstMapping(method=RequestMethod.GET, path="/all-products")
	@GetMapping("/all-products")
	public String getAllProducts(Model model) throws DaoException {
		// store the model data in a scope, which is made available to the view by DispatcherServlet 
		model.addAttribute("products", dao.getAllProducts());
		return "/WEB-INF/pages/show-products.jsp";
	}

}
