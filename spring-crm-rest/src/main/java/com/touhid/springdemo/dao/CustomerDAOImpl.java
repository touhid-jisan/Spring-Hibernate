package com.touhid.springdemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.touhid.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	private SessionFactory SessionFactory;

	@Override
	public List<Customer> getCustomers() {

		Session currentSession = SessionFactory.getCurrentSession();
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName" + Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session currentSession = SessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession = SessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		Session currentSession = SessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();

	}

}
