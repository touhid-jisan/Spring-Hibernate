package com.touhid.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touhid.springdemo.dao.CustomerDAO;
import com.touhid.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDAO customerDao;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDao.getCustomers();
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDao.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		customerDao.deleteCustomer(theId);
	}

}
