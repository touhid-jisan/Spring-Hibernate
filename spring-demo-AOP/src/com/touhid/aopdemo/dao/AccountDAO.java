package com.touhid.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {
	public void addAccount() {
		// getClass() gives us the class name for the display.
		System.out.println(getClass() + " : Doing my DB work. Adding an account");
	}
}
