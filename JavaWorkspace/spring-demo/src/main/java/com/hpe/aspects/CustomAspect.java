package com.hpe.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // AspectJ creates a proxy based on the pointcuts defined for advices here
@Component // required for component-scan
public class CustomAspect {
	
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

	@Before("execution( * com.hpe..*Dao.*(..) )")
	public void doLogBefore(JoinPoint jp) {
		log.info("going to invoke {} method of type {} with arguments {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType(),
				Arrays.toString(jp.getArgs()));
	}
	
	@After("execution( * com.hpe..*Dao.*(..) )")
	public void doLogAfter(JoinPoint jp) {
		log.info("returngin from {} method of type {}", 
				jp.getSignature().getName(),
				jp.getSignature().getDeclaringType());
	}
}
