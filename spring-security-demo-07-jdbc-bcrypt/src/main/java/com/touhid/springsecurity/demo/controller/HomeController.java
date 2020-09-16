package com.touhid.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	// add request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeader() {
		return "leaders";
	}
	
	@GetMapping("/system")
	public String showSystem() {
		return "system";
	}
}
