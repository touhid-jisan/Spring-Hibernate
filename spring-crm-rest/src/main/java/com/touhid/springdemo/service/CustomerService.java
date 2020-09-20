package com.touhid.springdemo.service;

import java.util.List;

import com.touhid.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();
	
	public Customer getCustomer(int theId);

	public void saveCustomer(Customer theCustomer);
	
	public void deleteCustomer(int theId);
	
}
