/**
 * 
 */
package com.sundevil.exceptionhandlers;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sundevil.exceptions.BankException;

/**
 * @author satyaswaroop boddu
 * 
 */
@Aspect
@Component
public class ExceptionInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionInterceptor.class);

	@AfterThrowing(pointcut = "within(com.sundevil.tryouts.web.*)", throwing = "t")
	public void toRuntimeException(Throwable t)
			throws BankException{
		if (t instanceof BankException) {
			logger.error(t.getMessage(), t);
			throw (BankException)t;
		
		}
		else {
			logger.error(t.getMessage(), t);
			BankException se = new BankException(t.getMessage(),t);
			se.setStackTrace(t.getStackTrace());
			throw se;
		}

	}

}
