package com.ctech.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctech.beans.UserBean;
import com.ctech.service.IUserService;

@Component("userValidator")
public class UserValidator implements Validator {
	@Autowired
	private IUserService userService;

	@Autowired
	private UserBean userBean;

	/**
     * This method has been used for validating the input user name fields.
     */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if (value != null) {
			if (userBean.getUser() != null
					&& (value.toString().equalsIgnoreCase(
							userBean.getUser().getLogin()) || value.toString()
							.equalsIgnoreCase(userBean.getUser().getEmail()))) {
			} else {
				if (!getUserService().isUsernameAvailable((String) value)
						|| !getUserService().isEmailAvailable((String) value)) {
					FacesMessage message = new FacesMessage();
					message.setDetail(value
							+ " is not available. Choose another name.");
					message.setSummary("Not available");
					message.setSeverity(FacesMessage.SEVERITY_WARN);
					throw new ValidatorException(message);
				}
			}
		}
	}

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}
