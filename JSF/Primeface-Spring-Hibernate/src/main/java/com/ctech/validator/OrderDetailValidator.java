package com.ctech.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;

import com.ctech.util.Util;


@Component("orderDetailValidator")
public class OrderDetailValidator implements Validator{

	/**
     * This method has been used for validating the input order fields.
     */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if(!Util.isNumeric((String) value)){
			FacesMessage message = new FacesMessage();
			message.setDetail("Only numbers allowed !");
			message.setSummary("Only numbers allowed !");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
	}

	
}
