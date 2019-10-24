package com.hpe.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // qualifies for component scan + scanned by HandlerMapping
public class ProductController {
	
	
	// @RequstMapping(method=RequestMethod.GET, path="/all-products")
	@GetMapping("/all-products")
	public String getAllProducts() {
		System.out.println("Getting all products...");
		return "/WEB-INF/pages/show-products.jsp";
	}

}
