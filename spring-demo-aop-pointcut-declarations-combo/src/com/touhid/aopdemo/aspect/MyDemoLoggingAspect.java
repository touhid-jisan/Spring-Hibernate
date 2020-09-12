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
	private void forDaoPackage() {
	}

	// pointcut for getter methods
	@Pointcut("execution(* com.touhid.aopdemo.dao.*.get*(..))")
	private void getter() {
	}

	// pointcut for setter methods
	@Pointcut("execution(* com.touhid.aopdemo.dao.*.set*(..))")
	private void setter() {
	}

	// pointcut for including package ... exclude getter / setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void noSetterNogetter() {
	}

	@Before("forDaoPackage()") // matches with any parameter
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====> Performing Add account advice");
	}

	@Before("noSetterNogetter()")
	public void performApiAnalytics() {
		System.out.println("\n=====> Performing API Analytics");
	}

}
