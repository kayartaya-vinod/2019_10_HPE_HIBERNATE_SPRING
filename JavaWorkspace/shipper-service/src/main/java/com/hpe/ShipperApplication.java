package com.hpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shippers")
@SpringBootApplication
public class ShipperApplication {

	@Autowired
	ShipperDao dao;

	@GetMapping("/{id}")
	public ResponseEntity<Shipper> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(dao.findById(id).get());
	}

	@GetMapping
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(dao.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(ShipperApplication.class, args);
	}
}
