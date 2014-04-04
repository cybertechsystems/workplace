package com.email.view;

import org.apache.log4j.Logger;

abstract public class BaseAction {

	private Logger LOGGER = Logger.getLogger("com.mailmodule.view.BaseAction");
	private String errorMessage = null;

	protected void manageError(Throwable e, String message, String code) {
		
		LOGGER.error("Exception in ManageTemplate-->save()" + e.getMessage());

	}

	protected String manageError(Throwable e, String message) {
		errorMessage = constructErrorMessage(e);
		return "errorPage";
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String constructErrorMessage(Throwable ex) {
		//ex.printStackTrace();

		StackTraceElement[] errors = ex.getStackTrace();
		StringBuilder errorStack = new StringBuilder();
		errorStack.append(ex.getMessage() + "\n STACK TRACE : \n");
		for (StackTraceElement stackTraceElement : errors) {
			errorStack.append(stackTraceElement.getClassName() + "."
					+ stackTraceElement.getMethodName() + " -> "
					+ stackTraceElement.getLineNumber() + "\n");
		}

		LOGGER.error(errorStack.toString());
		return errorStack.toString();
	}
}
