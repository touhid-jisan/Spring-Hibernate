package com.touhid.springdemo;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	// create an array of Strings
	private String[] data = {"Touhidul Islam Jisan", "Isra Binte Islam"};
	
	// create a radom generator
	private Random myRandom = new Random();
	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myRandom.nextInt(data.length);
		return "In Random Fortune Service , Name : " +  data[index];
	}

}
