package com.touhid.login;

public class LoginValidationService {
	
	public boolean isValid(String username, String password) {	
		if(username.equals("touhid") && password.equals("jisan")) {
			return true;
		}
		return false;
	}
	
}
