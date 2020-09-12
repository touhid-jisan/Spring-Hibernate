package com.touhid.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.touhid.aopdemo.dao.AccountDAO;
import com.touhid.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);

		theAccountDAO.addAccount(myAccount, false);
		theAccountDAO.doWork();
		myAccount.setLevel("LEVEL");
		myAccount.setName("Touhid");

		// call the accountDAO getter/setter methods
		theAccountDAO.setName("touhid");
		theAccountDAO.setServiceCode("coding");
 
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();

		// close the context
		context.close();

	}

}
