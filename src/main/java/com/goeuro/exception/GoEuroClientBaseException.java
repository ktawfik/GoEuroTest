package com.goeuro.exception;

import java.security.SecureRandom;

/**
 * This is the base exception abstract class for the client project
 * @author ktawfik
 *
 */
public abstract class GoEuroClientBaseException extends Exception {

	private final GoEuroError error;
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	private String devloperMessage;
	
	private final String uniqueIdentifier;
	
	public GoEuroClientBaseException(GoEuroError err){
		this(err, null);
	}
	
	public GoEuroClientBaseException(GoEuroError err, String msg){
		super(msg);
		this.error = err;
		this.uniqueIdentifier = String.valueOf(RANDOM.nextInt());
	}
	
	public ErrorDto toErrorDto(){
		ErrorDto errDto = new ErrorDto();
		errDto.setHttpStatus(error.getStatus().value());
		errDto.setMessage(error.getMessage());
		errDto.setDeveloperMessage(devloperMessage);
		return errDto;
	}
}
