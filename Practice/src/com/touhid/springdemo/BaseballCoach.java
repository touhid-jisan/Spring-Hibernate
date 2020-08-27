package com.touhid.springdemo;

public class BaseballCoach implements Coach{
	// define a private field for the dependency
	private FortuneService fortuneService;
	
	// define a constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService) {
		this.fortuneService  = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Batting Practice";
	}

	@Override
	public String getDailyFortune() {
		// use my fortune service to get a fortune
		return fortuneService.getFortune();
	}
}
