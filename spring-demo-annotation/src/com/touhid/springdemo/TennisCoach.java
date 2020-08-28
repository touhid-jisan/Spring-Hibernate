package com.touhid.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("thatSillyCoach")
@Component // default bean id is "tennisCoach"
public class TennisCoach implements Coach {
	
	// Field Injection with annotation
	@Autowired
	private FortuneService theFortuneService;
	
	// constructor injection
	/*
	 * @Autowired public TennisCoach(FortuneService theFortuneService) {
	 * this.theFortuneService = theFortuneService; }
	 */
	
	// default constructor
	
	public TennisCoach() {
		System.out.println("Inside TennisCoach default Constructor");
	}
 	
	// defining a setter method for injections, 
	// here we can use any method instead setFortuneService()
	// it will bea called method injection
	/*
	 * @Autowired public void setFortuneService(FortuneService theFortuneService) {
	 * System.out.println("TennisCoach: setFortuneService() method");
	 * this.theFortuneService = theFortuneService; }
	 * 
	 * // autowired methods must be used with parameters
	 * 
	 * @Autowired public void doSomeCrazyStaff(FortuneService theFortuneService) {
	 * System.out.println("TennisCoach: doSomeCrazyStaff() method"); }
	 */
	
	@Override
	public String getDailyWorkout() {
		return "Practice backhand";
	}
	
	@Override
	public String getDailyFortune() {
		return theFortuneService.getFortune();
	}

}
