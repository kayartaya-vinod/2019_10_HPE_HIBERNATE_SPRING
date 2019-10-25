package com.hpe.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.dao.CustomerDao;
import com.hpe.entity.Customer;
import com.hpe.entity.CustomerList;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource {

	@Autowired
	CustomerDao dao;

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable String id) {
		return dao.findById(id).get();
	}

	@GetMapping(produces = { "application/json" })
	public Iterable<Customer> getAllCustomers() {
		return dao.findAll();
	}

	@GetMapping(produces = { "application/xml" })
	public CustomerList getAllCustomersAsXml() {
		List<Customer> list = new ArrayList<>();
		dao.findAll().forEach(c -> list.add(c));
		return new CustomerList(list);
	}

	// http://localhost:9999/api/customers/by-phone?p=(5) 555-4729
	@GetMapping(path = "/by-phone", produces = "application/json")
	public ResponseEntity<Customer> getCustomerByPhone(@RequestParam("p") String phone) {
		Customer cust = dao.findByPhone(phone);
		if (cust == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cust);
	}

	@PostMapping(produces = { "application/json" }, 
			consumes = { "application/xml", "application/json" })
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer) {
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			dao.save(customer);
			map.put("success", true);
			map.put("data", customer);
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put("success", false);
			map.put("error", e.getMessage());
			return ResponseEntity.status(500).body(map);
		}
		
	}

}
