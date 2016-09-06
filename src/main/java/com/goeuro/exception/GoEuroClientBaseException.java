package com.goeuro.exception;

import java.security.SecureRandom;

/**
 * This is the base exception abstract class for the client project
 * @author ktawfik
 *
 */
public abstract class GoEuroClientBaseException extends Exception {

	private final GoEuroError error;
	// this random id will identify the specific Exception and that will be used for further investigations if the exceptions
	// are dumped into any kind of permenant data source
	private static final SecureRandom RANDOM = new SecureRandom();
	// a message to the developer if needed.
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
