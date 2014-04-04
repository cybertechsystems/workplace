package com.email.util;

/**
 * Use to handle Exception.
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	private String msgKey;
	private String shortMessage;
	
	protected ApplicationException() {}

	public ApplicationException(Exception exception, String errorMessageKey) {
		super(exception);
		msgKey = errorMessageKey;
		shortMessage = exception.getMessage();
	}
	
	public ApplicationException(String errorMessageKey) {
		msgKey = errorMessageKey;
	}

	public String getMsgKey() {
		return msgKey;
	}

	public String getShortMessage() {
		return shortMessage;
	}
}
