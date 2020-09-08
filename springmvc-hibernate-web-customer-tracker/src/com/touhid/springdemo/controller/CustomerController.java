package com.touhid.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.touhid.springdemo.dao.CustomerDAO;
import com.touhid.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the dao - customer dao  
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomer(Model model) {
		
		// get customers from add
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		
		return "list-customer";
	}
}
