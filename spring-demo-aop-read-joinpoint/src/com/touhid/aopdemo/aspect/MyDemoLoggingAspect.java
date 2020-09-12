package com.touhid.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.touhid.aopdemo.Account;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	@Before("com.touhid.aopdemo.aspect.AopExpressions.noSetterNogetter()") // matches with any parameter
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=====> Performing Add account advice");
		
		// display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("method signeture ======" + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop args
		for(Object tempArg : args) {
			System.out.println(tempArg);
			if(tempArg instanceof Account) {
				// downcast and print Account sprecific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name" + theAccount.getName());

				System.out.println("account level" + theAccount.getLevel());
			}
		}
	}
	
	
	
}
