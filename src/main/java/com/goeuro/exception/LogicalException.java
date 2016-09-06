package com.goeuro.exception;

/**
 * This class represents the Logical exceptions happenned in the system
 * @author ktawfik
 *
 */
public class LogicalException extends GoEuroClientBaseException {

	public LogicalException(GoEuroError err) {
		this(err, err.getMessage());
	}
	
	public LogicalException(GoEuroError err, String msg){
		super(err, msg);
	}
	
}
