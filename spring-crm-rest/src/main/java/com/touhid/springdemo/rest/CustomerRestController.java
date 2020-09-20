package com.touhid.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touhid.springdemo.entity.Customer;
import com.touhid.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{theId}")
	public Customer getCustomer(@PathVariable int theId) {
		
		Customer theCustomer = customerService.getCustomer(theId);
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer not found: " + theId);
		}
		return customerService.getCustomer(theId);
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers") 
	public Customer updateCustomer(@RequestBody Customer theCustomer){	
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{theId}")
	public String deleteCustomer(@PathVariable int theId) {
		Customer theCustomer = customerService.getCustomer(theId);
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer not found: "+ theId);
		}
		customerService.deleteCustomer(theId);
		
		return ("Deleted Customer id - " + theId); 	
	}
}
