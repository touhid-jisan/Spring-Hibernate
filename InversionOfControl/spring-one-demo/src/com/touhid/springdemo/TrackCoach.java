package com.touhid.springdemo;

public class TrackCoach implements Coach {
	private FortuneService fortuneService;
	
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	public TrackCoach() {
		
	}
	
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Run 5k";
	}
	public String getDailyFortune() {
		return "just do it(track coach) : " +fortuneService.getFortune(); 
	}
}
