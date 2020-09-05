package com.touhid.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	LoginValidationService validService = new LoginValidationService();
	
	@RequestMapping(value="/hello", method=RequestMethod.GET )
	public String showLoginPage(){
		return "login";
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.POST )
	public String showWelcomePage(ModelMap model, @RequestParam String user_name, @RequestParam String user_password){
		if(!validService.isValid(user_name, user_password)) {
			model.put("errorMessage", "Wrong Username or password");
			return "login";
		}
		model.put("username", user_name);
		model.put("password", user_password);
		return "welcome";
	}
}