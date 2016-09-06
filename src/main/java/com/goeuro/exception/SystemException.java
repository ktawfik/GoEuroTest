package com.goeuro.exception;

public class SystemException extends GoEuroClientBaseException {

	public SystemException(GoEuroError err) {
		this(err, err.getMessage());
	}
	
	public SystemException(GoEuroError err, String msg) {
		super(err, msg);
	}

}
