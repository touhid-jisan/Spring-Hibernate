package com.touhid.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
	
		// load the spring cofig file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		// retrive bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		  
		// check if they are same 
		System.out.println(theCoach == alphaCoach);
		System.out.println("\nMemory Location for theCoach: " + theCoach);
		System.out.println("\nMemory Location for alphaCoach: " + alphaCoach);
		
		// close the context
		context.close();
	}

}
