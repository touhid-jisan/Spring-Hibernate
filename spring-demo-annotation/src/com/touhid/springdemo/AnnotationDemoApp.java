package com.touhid.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// get the bean from spring contatiner
		//Coach theCoach = context.getBean("thatSillyCoach" , Coach.class); 
		Coach theCoach = context.getBean("tennisCoach" , Coach.class); 
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call a method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
				
		// close the context
		context.close();
	}
}
