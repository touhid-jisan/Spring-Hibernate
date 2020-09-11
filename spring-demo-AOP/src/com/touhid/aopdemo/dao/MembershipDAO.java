package com.touhid.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public boolean addSillyMember() {
		System.out.println((getClass() + " membership account"));
		return true;
	}
	
	public void goToSleep () {
		System.out.println(getClass()+" i am going to sleep");
	}
	
}
