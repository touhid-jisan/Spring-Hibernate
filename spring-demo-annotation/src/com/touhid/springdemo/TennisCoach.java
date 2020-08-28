package com.touhid.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


//@Component("thatSillyCoach")
@Component // default bean id is "tennisCoach"
//@Scope("prototype")
public class TennisCoach implements Coach {
	
	// Field Injection with annotation
	@Autowired
	@Qualifier("randomFortuneService")
	//@Qualifier("happyFortuneService")
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

	// define init method
	// code will execute after constructor and after injection of dependencies
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("TennisCoach : inside my doMyStartUpStuff() method");
	}
	
	
	// define destroy method
	// code will execute before bean is distroyed
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("TennisCoach : inside my doMyCleanUpStuff() method");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
