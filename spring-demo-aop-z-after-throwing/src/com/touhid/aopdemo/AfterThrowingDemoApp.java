package com.touhid.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.touhid.aopdemo.dao.AccountDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		// call the mehtod to find the accounts
		List<Account> theAccounts = null;
		try {
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception e) {
			System.out.println("\n Main Program : Caught Exception : " + e);
		}
		
		// display the accounts
		System.out.println("\n main program : AfterThrowingDemoApp\n-----------\n" + theAccounts + "\n");

		// close the context
		context.close();

	}

}
