package com.goeuro.exception;

import org.springframework.http.HttpStatus;
/**
 * 
 * @author ktawfik
 *
 * This class contains all errors handled by the application, contains (message, type and httpStatus)
 */
public enum GoEuroError {

	GOEURO_001("Invalid application Parameters"), 
	GOEURO_002("No content matched your query", Type.INFO, HttpStatus.NO_CONTENT),
	GOEURO_003("Invalid csv file name passed"),
	GOEURO_004("Empty list passed to be written to csv file"),
	GOEURO_005("Empty columns name headers for csv file passed"),
	GOEURO_006("Empty or invalid parameter passed"),
	
	/// system errors
	GOEURO_007("Provided file name not found while trying to write csv file.", HttpStatus.INTERNAL_SERVER_ERROR),
	GOEURO_008("Error in generating the csv schema", HttpStatus.INTERNAL_SERVER_ERROR),
	GOEURO_009("Error in mapping the csv schema, check header columns", HttpStatus.INTERNAL_SERVER_ERROR),
	GOEURO_010("File not found", HttpStatus.INTERNAL_SERVER_ERROR);


	public enum Type {
		ERROR, WARNING, INFO;
	};

	private Type type;
	private transient HttpStatus status;
	private boolean notifyDevelopers;
	private final String message;

	
	private GoEuroError(String message) {
		this(message, HttpStatus.BAD_REQUEST);
	}

	private GoEuroError(String message, HttpStatus status){
		this(message, Type.ERROR, status, false);
	}
	
	private GoEuroError(String message, Type t) {
		this(message, t, HttpStatus.BAD_REQUEST, false);
	}
	
	private GoEuroError(String message, Type t , HttpStatus status) {
		this(message, t, status, false);
	}
	
	private GoEuroError(String message, Type t, HttpStatus status, boolean notifyDevloper) {
		this.type = t;
		this.status = status;
		this.notifyDevelopers = notifyDevloper;
		this.message = message;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public boolean isNotifyDevelopers() {
		return notifyDevelopers;
	}

	public void setNotifyDevelopers(boolean notifyDevelopers) {
		this.notifyDevelopers = notifyDevelopers;
	}

	public String getMessage() {
		return message;
	}


}
