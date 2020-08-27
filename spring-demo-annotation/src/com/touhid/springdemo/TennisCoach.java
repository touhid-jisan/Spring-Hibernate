package com.touhid.springdemo;

import org.springframework.stereotype.Component;

//@Component("thatSillyCoach")
@Component // default bean id is "tennisCoach"
public class TennisCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Practice backhand";
	}

}
