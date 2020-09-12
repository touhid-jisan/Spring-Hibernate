package com.touhid.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.touhid.aopdemo.Account;

@Component
public class AccountDAO {
	private String name;
	private String serviceCode;
	
	public List<Account> findAccounts () {
		List<Account> myAccounts = new ArrayList<>();
		
		Account temp1 = new Account("Jhon" , "Silver");
		Account temp2 = new Account("Touhid" , "Dimond");
		Account temp3 = new Account("Jhon" , "Gold");

		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}
	
	public String getName() {
		System.out.println(getClass() + " in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " in setServiceCode()");
		this.serviceCode = serviceCode;
	}
	
	public void addAccount(Account theAccount, boolean flag) {
		// getClass() gives us the class name for the display.
		System.out.println(getClass() + " : Method signature account" + flag);
	}
	
	public boolean doWork () {
		System.out.println(getClass() + " doing stuff");
		return false;
	}
	
}
