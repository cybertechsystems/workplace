package com.zsg.dcvps.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zsg.dcvps.entity.Orders;
import com.zsg.dcvps.entity.User;
import com.zsg.dcvps.service.IUserService;
import com.zsg.dcvps.util.Util;

@Component("registerBean")
@RequestScoped
public class RegisterBean implements Serializable {

	private static final Logger LOG = Logger.getLogger(RegisterBean.class);

	private User user = new User();
	
	private Orders orders = new Orders();
	
	private String password2;

	@Autowired
	private UserBean userBean;

	@Autowired
	private OrderBean orderBean; 
	
	@Autowired
	private IUserService userService;

	public void save() {
		LOG.debug("save...");
		try {
			validate();
			userService.save(user);

			Util.addInfoMsg("User created", "Login name is: " + getUser().getLogin());

			if (user.getId() != null) {
				userBean.setUser(user);
				userBean.setPage("../user/welcome");
			} else {
				Util.addErrorMsg("Registering failed", "Error while registering user ");
			}
			getUser().setPassword(null);
			setPassword2(null);
		} catch (ValidatorException e) {
			LOG.error("Validation exception: " + e.getFacesMessage().getSummary());
			LOG.error("Error while creating user", e);
			Util.addMsg(e.getFacesMessage());
		} catch (Exception e) {
			LOG.error("Error while creating user", e);
			Util.addErrorMsg("Registering failed", "Error while registering user: " + e.getMessage());
		}
	}

	public void login() {
		LOG.debug("login...");
		try {
			loginValidate();
			User user = userService.findUserDetails(getUser().getLogin(),
					Util.getMD5Password(getUser().getPassword()));

			if (user != null && user.getId() != null) {
				if (user.getRoleList() != null
						&& "ADMIN".equalsIgnoreCase(user.getRoleList().get(0).getRoleName())) {
					userBean.setUser(user);
					orderBean.setUser(user);
					Util.addInfoMsg("Login succesful", "You are logged in. Welcome back: " + getUser().getLogin());
					orderBean.setPage("../order/order");
					orderBean.initializeDataModel();
				} else {
					userBean.setUser(null);
					orderBean.setUser(null);
					Util.addErrorMsg("Permission Denied !", "");
				}
			} else {
				Util.addErrorMsg("The username or password you entered is incorrect !", "");
			}
			//getUser().setPassword(null);
			setPassword2(null);
		} catch (Exception e) {
			LOG.error("Error while login", e);
			//Util.addErrorMsg("Login failed !", "Error while login: " + e.getMessage());
		}
	}
	
	private void loginValidate() throws ValidatorException{
		FacesMessage message = new FacesMessage();
		if ((getUser().getLogin() == null || getUser().getLogin().trim().isEmpty()) && 
				(getUser().getPassword() == null || getUser().getPassword().trim().isEmpty())) {
			message.setDetail("Please enter the login fields.");
			message.setSummary("Login fields are not set !");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			Util.addErrorMsg("Login fields are not set !","");
			throw new ValidatorException(message);
		}
		if (getUser().getLogin() == null || getUser().getLogin().trim().isEmpty()) {
			message.setDetail("Please enter a user name.");
			message.setSummary("Login name is not set !");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			Util.addErrorMsg("Login name is not set !","");
			throw new ValidatorException(message);
		}
		if (getUser().getPassword() == null || getUser().getPassword().trim().isEmpty()) {
			message.setDetail("Please enter a password.");
			message.setSummary("Password is not set !");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			Util.addErrorMsg("Password is not set !","");
			throw new ValidatorException(message);
		}
	}

	private void validate() throws ValidatorException {
		if (getUser().getLogin() == null || getUser().getLogin().trim().isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Please enter a login name.");
			message.setSummary("Login name not set");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}

		if (getUser().getEmail() == null || getUser().getEmail().trim().isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Please enter a email adress.");
			message.setSummary("Email adress not set");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
		if (getUser().getPassword() == null || getUser().getPassword().trim().isEmpty()) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Please enter a password.");
			message.setSummary("Passwords not set");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}
		if (!getUser().getPassword().equals(password2)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Please retype the password correctly.");
			message.setSummary("Passwords doesn't match");
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			throw new ValidatorException(message);
		}

	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void showRegistration() {
		userBean.setPage("../user/register");
	}

	public void showLogin() {
		userBean.setPage("../component/login");
	}

	public void cancel() {
		userBean.setPage("../user/welcome");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
