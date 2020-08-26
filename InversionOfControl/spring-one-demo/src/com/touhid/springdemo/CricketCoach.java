package com.touhid.springdemo;

public class CricketCoach implements Coach {

	private FortuneService fortuneService;
	
	// create a no-arg constructor
	public CricketCoach() {
		System.out.println("CricketCoach : inside");
	}
	
	// our setter method
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("cricket coach : inside the setter method");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Practice Fast Bowling";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

}
