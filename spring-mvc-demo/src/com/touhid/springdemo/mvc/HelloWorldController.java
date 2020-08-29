package com.touhid.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	// need a controller method to show initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form"; 
	}
 	
	// need a method to process the HTML form
	@RequestMapping("processForm")
	public String processForm() {
		return "helloworld";
	}
}
