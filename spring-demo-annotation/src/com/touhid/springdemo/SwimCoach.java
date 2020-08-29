package com.touhid.springdemo;

public class SwimCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public SwimCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "SwimCoach: getDailyWorkout() method";
	}

	@Override
	public String getDailyFortune() {
		return "SwimCoach: getDailyFortune() method";
	}

}
