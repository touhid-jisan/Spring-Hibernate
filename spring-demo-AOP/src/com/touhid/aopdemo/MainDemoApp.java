package com.touhid.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.touhid.aopdemo.dao.AccountDAO;
import com.touhid.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class); 
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get the membership bean from spring container

		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		theAccountDAO.addAccount();
		
		System.out.println("\n  lets call the addAccount method again");
		theAccountDAO.addAccount();
		
		System.out.println("\n \n before calling membership dao" );
		theMembershipDAO.addSillyMember();
		
		// close the context
		context.close();
		
	}

}
