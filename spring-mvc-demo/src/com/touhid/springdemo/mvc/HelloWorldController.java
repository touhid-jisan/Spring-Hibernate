package com.touhid.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
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
	
	// a new controller method to read data and
	// and data to the model
	@RequestMapping("/processFormV2")
	public String readData(HttpServletRequest request, Model model) {
		
		//read the request parameter from the HTML form
		String theName = request.getParameter("studentName");
		
		// convert the data to all caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Yo!! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
			
		return "helloworld";
	}
	
	@RequestMapping("/processFormV3")
	public String readDataV2(@RequestParam("studentName") String theName, Model model) {
		// used @RequestParam to get the value of theName
		
		// convert the data to all caps
		theName = theName.toUpperCase();
		
		// create the message
		String result = "Yo My Friend v3!! " + theName;
		
		// add message to the model
		model.addAttribute("message", result);
			
		return "helloworld";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
