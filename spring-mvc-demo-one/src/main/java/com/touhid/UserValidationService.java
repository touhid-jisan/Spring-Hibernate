package com.touhid;

public class UserValidationService {
	
	public boolean isValid(String username, String password) {
		if(username.equals("touhid") && password.equals("jisan")) {
			return true;
		}
		return false;
	}
}
