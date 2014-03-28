package com.zsg.dcvps.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;

@Component("loginValidator")
public class LoginValidator implements Validator {
	
	/**
     * This method has been used for validating the input login fields.
     */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if(value == null || value.toString().trim().length() == 0){
			FacesMessage message = new FacesMessage();
			message.setDetail("Field is Mandatory !");
			message.setSummary("Username field is mandatory !");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}
}
