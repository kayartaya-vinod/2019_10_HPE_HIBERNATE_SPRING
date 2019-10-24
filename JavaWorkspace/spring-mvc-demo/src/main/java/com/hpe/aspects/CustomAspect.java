package com.hpe.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.hpe.dao.DaoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // AspectJ creates a proxy based on the pointcuts defined for advices here
@Component // required for component-scan
public class CustomAspect {
	
	@Pointcut("execution( * com.hpe..*Dao.*(..) )")
	public void pc1() {}
	
	@Pointcut("execution( * com.hpe..*Service.*(..) )")
	public void pc2() {}
	
	
	
	@AfterThrowing(throwing = "ex", pointcut = "pc1()")
	public void convertToDaoException(Exception ex) throws DaoException {
		throw new DaoException(ex);
	}
	
	// ProceedingJoinPoint can be used only in an Around advice
	@Around("execution( * com.hpe..ProductDao.get*(Double, Double))")
	public Object swapInputs(ProceedingJoinPoint pjp) throws Throwable {
		// do something
		log.info("Testing if max < min...");
		Object[] args = pjp.getArgs();
		Double d1 = (Double) args[0];
		Double d2 = (Double) args[1];
		if(d2<d1) {
			log.info("given max is less than min, so swapping...");
			args = new Object[] { d2, d1};
		}
		// proceed to the target method
		Object retVal = pjp.proceed(args);
		
		// do something
		log.info("successfully got return value from {} method of {}", 
				pjp.getSignature().getName(),
				pjp.getSignature().getDeclaringType());
		
		return retVal;
	}
	
	
	@Around("pc1() || pc2()")
	public Object doLogAround(ProceedingJoinPoint jp) throws Throwable {
		log.info("going to invoke {} method of type {} with arguments {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType(),
				Arrays.toString(jp.getArgs()));
		Object obj = jp.proceed();
		log.info("returning from {} method of type {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType());
		return obj;
	}

	//@Before("execution( * com.hpe..*Dao.*(..) )")
	public void doLogBefore(JoinPoint jp) {
		log.info("going to invoke {} method of type {} with arguments {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType(),
				Arrays.toString(jp.getArgs()));
	}
	
	//@After("execution( * com.hpe..*Dao.*(..) )")
	public void doLogAfter(JoinPoint jp) {
		log.info("returning from {} method of type {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType());
	}
}
