package com.touhid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// this is where we add all of our related advices for logging
	//lets start with an @Before advice
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void com.touhid.aopdemo.dao.AccountDAO.addAccount())") // fully qualified class name: package+class
	//@Before("execution(public void add*())")
	@Before("execution(boolean add*())") // modifier is optional	
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====> Executing @Before advice on addAccount");
	}
}
