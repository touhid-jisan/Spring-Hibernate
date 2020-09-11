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
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		
		
		theAccountDAO.addAccount(myAccount, false);
		theAccountDAO.doWork();
		
		System.out.println("\n \n before calling membership dao" );
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		
		// close the context
		context.close();
		
	}

}
