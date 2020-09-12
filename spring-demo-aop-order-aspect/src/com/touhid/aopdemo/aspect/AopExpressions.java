package com.touhid.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {

	@Pointcut("execution(* com.touhid.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	// pointcut for getter methods
	@Pointcut("execution(* com.touhid.aopdemo.dao.*.get*(..))")
	public void getter() {
	}

	// pointcut for setter methods
	@Pointcut("execution(* com.touhid.aopdemo.dao.*.set*(..))")
	public void setter() {
	}

	// pointcut for including package ... exclude getter / setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void noSetterNogetter() {
	}
}
