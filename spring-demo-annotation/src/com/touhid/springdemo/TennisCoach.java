package com.touhid.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("thatSillyCoach")
@Component // default bean id is "tennisCoach"
public class TennisCoach implements Coach {

	private FortuneService theFortuneService;
	
	@Autowired
	public TennisCoach(FortuneService theFortuneService) {
		this.theFortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice backhand";
	}
	
	@Override
	public String getDailyFortune() {
		return theFortuneService.getFortune();
	}

}
