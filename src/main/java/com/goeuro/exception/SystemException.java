package com.goeuro.exception;

/**
 * @author ktawfik
 * This class represents the Server exceptions happened while running the system, e.g. cannot open file, cannot read file
 * 
 *
 */
public class SystemException extends GoEuroClientBaseException {

	public SystemException(GoEuroError err) {
		this(err, err.getMessage());
	}
	
	public SystemException(GoEuroError err, String msg) {
		super(err, msg);
	}

}
