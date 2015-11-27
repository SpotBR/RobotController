package com.mechani.robotcontroller.communication.exceptions;

public class RequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8177127841874963403L;

	private RobotError errorType;
	
	public RequestException(String message, RobotError error) {
		super(message);
	}

	public RobotError getErrorType() {
		return errorType;
	}

	public void setErrorType(RobotError errorType) {
		this.errorType = errorType;
	}
	
}
