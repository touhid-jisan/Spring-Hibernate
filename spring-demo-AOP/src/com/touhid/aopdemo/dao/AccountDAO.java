package com.touhid.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.touhid.aopdemo.Account;

@Component
public class AccountDAO {
	/*
	 * public void addAccount() { // getClass() gives us the class name for the
	 * display. System.out.println(getClass() +
	 * " : Doing my DB work. Adding an account"); }
	 */
	
	
	/*
	 * public void addAccount(Account theAccount) { // getClass() gives us the class
	 * name for the display. System.out.println(getClass() +
	 * " : Method signature accont"); }
	 */
	
	public void addAccount(Account theAccount, boolean flag) {
		// getClass() gives us the class name for the display.
		System.out.println(getClass() + " : Method signature account" + flag);
	}
	
	public boolean doWork () {
		System.out.println(getClass() + " doing stuff");
		return false;
	}
	
}
