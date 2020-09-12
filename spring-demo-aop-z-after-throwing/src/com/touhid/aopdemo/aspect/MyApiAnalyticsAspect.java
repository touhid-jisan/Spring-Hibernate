package com.touhid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyApiAnalyticsAspect {
	
	@Before("com.touhid.aopdemo.aspect.AopExpressions.noSetterNogetter()")
	public void performApiAnalytics() {
		System.out.println("\n=====> Performing API Analytics");
	
	}
}
