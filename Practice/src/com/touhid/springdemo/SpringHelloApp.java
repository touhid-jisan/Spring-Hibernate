package com.touhid.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {

	public static void main(String[] args) {
		
		// load the spring config file 
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrive bean from spring controler
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// lets call out new method for fortunes
		System.out.println(theCoach.getDailyFortune());
		// 
		
		
		
		
		
		// close the context 
		context.close();
	}

}
