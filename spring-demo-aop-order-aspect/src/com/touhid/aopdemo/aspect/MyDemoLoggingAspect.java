package com.touhid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	@Before("com.touhid.aopdemo.aspect.AopExpressions.noSetterNogetter()") // matches with any parameter
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====> Performing Add account advice");
	}
	
	
	// Refactor these codes in new Expression class - because these are common ---------------------------
	/*
	 * @Pointcut("execution(* com.touhid.aopdemo.dao.*.*(..))") private void
	 * forDaoPackage() { }
	 * 
	 * // pointcut for getter methods
	 * 
	 * @Pointcut("execution(* com.touhid.aopdemo.dao.*.get*(..))") private void
	 * getter() { }
	 * 
	 * // pointcut for setter methods
	 * 
	 * @Pointcut("execution(* com.touhid.aopdemo.dao.*.set*(..))") private void
	 * setter() { }
	 * 
	 * // pointcut for including package ... exclude getter / setter
	 * 
	 * @Pointcut("forDaoPackage() && !(getter() || setter())") private void
	 * noSetterNogetter() { }
	 */
	

	// Refactor these methods to Use @Order ---------------------------------------------------------------
	/*
	 * @Before("noSetterNogetter()") public void performApiAnalytics() {
	 * System.out.println("\n=====> Performing API Analytics"); }
	 * 
	 * @Before("noSetterNogetter()") public void logToCloudAsync() {
	 * System.out.println("\n=====> Loggin to Cloud in async fashion"); }
	 */
}
