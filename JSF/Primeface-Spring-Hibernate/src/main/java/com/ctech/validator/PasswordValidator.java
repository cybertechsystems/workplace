package com.ctech.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctech.beans.UserBean;
import com.ctech.util.Util;

@Component("passwordValidator")
public class PasswordValidator implements Validator{
	
	/**
     * This method has been used for validating the input password fields.
     */
    @Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String pwd = (String) value;
		try {
			if (pwd != null && pwd.length() < 6) {
				FacesMessage message = new FacesMessage();
				message.setDetail("Password is to short !");
				message.setSummary("Enter a password with at least 6 characters. !");
				message.setSeverity(FacesMessage.SEVERITY_WARN);
				throw new ValidatorException(message);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
