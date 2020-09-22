package com.touhid.springboot.demo.springbootapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


//@RequestMapping("/api")
@RestController
public class AppRestController {
	
	@Value("${coach.name}")
	private String coachName;
	
	@Value("${team.name}")
	private String teamName;
	
	// expose new endpoint for "team 
	@GetMapping("/teaminfo")
	public String getTeamInfo() {
		return "Coach: " + coachName + " Team Name: " + teamName;
	}

    //@GetMapping("/hello")
    @GetMapping("/")
    public String sayHello () {
        return "Hello world!!!, Tim Server is" + LocalDateTime.now();
    }

    @GetMapping("/check")
    public String check() {
        return "check";
    }
}
