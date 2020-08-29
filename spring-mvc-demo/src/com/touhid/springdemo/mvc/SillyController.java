package com.touhid.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SillyController {
	
	@RequestMapping("/showForm")
	public String displayForm() {
		return "helloworld-form";
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
