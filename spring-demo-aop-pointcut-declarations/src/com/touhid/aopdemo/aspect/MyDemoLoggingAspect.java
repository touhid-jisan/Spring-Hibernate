package com.touhid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	@Pointcut("execution(* com.touhid.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Before("forDaoPackage()") // matches with any parameter
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====> Performing Add accout advice");
	}
	
	
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		System.out.println("\n=====> Performing API Analytics");
	}
}
