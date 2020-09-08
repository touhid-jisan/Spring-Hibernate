package com.touhid.springdemo.dao;

import java.util.List;

import com.touhid.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	
}
