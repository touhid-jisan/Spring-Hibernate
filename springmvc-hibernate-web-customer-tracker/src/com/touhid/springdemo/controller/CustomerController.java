package com.touhid.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.touhid.springdemo.dao.CustomerDAO;
import com.touhid.springdemo.entity.Customer;
import com.touhid.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// inject the dao - customer service  
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customer";
	}
	
	@GetMapping("/new-customer")
	public String newCustomerPage(Model model) {
		Customer theCustomer = new Customer();
		model.addAttribute("customer" , theCustomer);
		return "add-customer";
	}
	
	@PostMapping("/add-customer")
	public String addCustomer(@ModelAttribute("customer") Customer customer, BindingResult result ) {
		if(result.hasErrors()) {
			return "add-customer";
		} 
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model){
		Customer theCustomer = customerService.getCustomer(id); 
		
		model.addAttribute("customer", theCustomer);
			 
		return "add-customer";
	}
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id, Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
