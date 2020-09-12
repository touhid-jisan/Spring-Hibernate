package com.touhid.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	
	
	@AfterReturning(pointcut="execution(* com.touhid.aopdemo.dao.AccountDAO.findAccounts(..))",
					returning = "result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// post process  / modifying data
		if(!result.isEmpty()) {
			Account tempAccount = result.get(0);
			tempAccount.setName("Modifed Name = Jisan");
		}
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("=======================> executing @AfterReturning on method " + method);
		// print out the result of the method call
		System.out.println("=======================> result is " + result);
		
		// lets post process the data 
		// convert the account name to uppercase
		convertAccoutName(result);
		
		System.out.println("==============  > >  " + result);
	}
		
	private void convertAccoutName(List<Account> result) {
		for(Account tempAccount: result) {
			String theUpperCase = tempAccount.getName().toUpperCase();
			tempAccount.setName(theUpperCase);
		}
		
	}

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
