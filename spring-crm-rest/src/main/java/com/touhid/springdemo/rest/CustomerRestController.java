package com.touhid.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touhid.springdemo.entity.Customer;
import com.touhid.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	private List<Customer> theCustomers;

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		theCustomers = customerService.getCustomers();	
		return theCustomers;
	}
	
	@GetMapping("/customers/{theId}")
	public Customer getCustomer(@PathVariable int theId) {
		
		return customerService.getCustomer(theId);
	}
	
	
}
