package com.zsg.dcvps.validator;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsg.dcvps.beans.UserBean;
 
@Component("emailValidator")
public class EmailValidator implements Validator{
	@Autowired
	private UserBean userBean;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";
 
    private Pattern pattern;
    private Matcher matcher;
    
    @Autowired
    UserValidator userValidator;
 
    public EmailValidator(){
          pattern = Pattern.compile(EMAIL_PATTERN);
    }
 
    /**
     * This method has been used for validating the input email fields.
     */
    @Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());
		if (userBean.getUser() != null
				&& value.toString().equalsIgnoreCase(
						userBean.getUser().getEmail())) {
		} else {
			if (!matcher.matches()) {
				FacesMessage msg = new FacesMessage("Email validation failed.",
						"Invalid email format.");
				msg.setSeverity(FacesMessage.SEVERITY_WARN);
				throw new ValidatorException(msg);
			}
		}
		getUserValidator().validate(context, component, value);
	}

    /**
     * @return the userValidator
     */
    public UserValidator getUserValidator() {
        return userValidator;
    }

    /**
     * @param userValidator the userValidator to set
     */
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
