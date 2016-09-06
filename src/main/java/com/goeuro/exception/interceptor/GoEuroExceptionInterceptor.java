package com.goeuro.exception.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.goeuro.exception.GoEuroClientBaseException;
/**
 * 
 * @author ktawfik
 * This is the Exception interceptor for the GoEuro client app.
 * handle exceptions occurred and report the relevent message to the cosole.
 * and 
 */
@Aspect
@Controller
public class GoEuroExceptionInterceptor {
	
	@Value(value="${goeuro.client.test_mode}")
	private boolean isTestMode;
	/**
	 * this method called around all methods under com.goeuro package and handle the exception on its own,
	 * I decided to use Around rather than AfterThrow, as here I can control the flow, while in case of AfterException
	 * the exception will be already thrown and I can't get back again to the application flow.
	 * 
	 * Also I decided in case of exception will show the message and exit the system.
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("execution(* com.goeuro.*.*(..))")
	public Object methodAspect(ProceedingJoinPoint joinPoint) throws Throwable{
		Object o = null;
		try{
			
			o = joinPoint.proceed();
		}catch(GoEuroClientBaseException e){
			System.err.println(e.toErrorDto());
			// dun call system.exit in case of testMode
			if(!isTestMode())
				System.exit(0);
		}
		return o;
	}
	
	/**
	 * work around to make 
	 * @param c
	 * @return
	 */
	boolean isTestMode(){
		return isTestMode;
	}
}
