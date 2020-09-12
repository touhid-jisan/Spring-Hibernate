package com.touhid.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.touhid.aopdemo.Account;

@Component
public class AccountDAO {
	private String name;
	private String serviceCode;
	
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
